package com.base.project.core.network

import com.base.project.core.common.Result
import kotlinx.coroutines.flow.*
import timber.log.Timber

/**
 * A generic class that can provide a resource backed by both the cache and network.
 * 
 * You can read more about it in the [Architecture Guide](https://developer.android.com/arch).
 * 
 * @param ResultType Type for the Resource data.
 * @param RequestType Type for the API response.
 */
abstract class NetworkBoundResource<ResultType, RequestType> {

    fun asFlow(): Flow<Result<ResultType>> = flow {
        // Emit loading state
        emit(Result.Loading)
        
        try {
            // Check if we should fetch from network
            val shouldFetch = shouldFetch(loadFromDb().first())
            
            if (shouldFetch) {
                // Emit cached data first (if available)
                val cachedData = loadFromDb().first()
                if (cachedData != null && shouldEmitCachedData()) {
                    emit(Result.Success(cachedData))
                }
                
                try {
                    // Fetch from network
                    val networkResponse = createCall()
                    
                    // Save network result to cache
                    saveCallResult(networkResponse)
                    
                    // Emit fresh data from cache
                    emitAll(loadFromDb().map { Result.Success(it) })
                    
                } catch (exception: Exception) {
                    Timber.e(exception, "Network call failed")
                    
                    // If network fails, try to emit cached data
                    val cachedData = loadFromDb().first()
                    if (cachedData != null) {
                        emit(Result.Success(cachedData))
                    } else {
                        emit(Result.Error(exception))
                    }
                }
            } else {
                // Just emit cached data
                emitAll(loadFromDb().map { Result.Success(it) })
            }
        } catch (exception: Exception) {
            Timber.e(exception, "Error in NetworkBoundResource")
            emit(Result.Error(exception))
        }
    }

    /**
     * Called to save the result of the API response into the database.
     */
    protected abstract suspend fun saveCallResult(response: RequestType)

    /**
     * Called with the data in the database to decide whether to fetch
     * potentially updated data from the network.
     */
    protected abstract suspend fun shouldFetch(data: ResultType?): Boolean

    /**
     * Called to get the cached data from the database.
     */
    protected abstract fun loadFromDb(): Flow<ResultType?>

    /**
     * Called to create the API call.
     */
    protected abstract suspend fun createCall(): RequestType

    /**
     * Called to decide whether to emit cached data when network call is initiated.
     * Default is true.
     */
    protected open fun shouldEmitCachedData(): Boolean = true
}

/**
 * Simplified version for cases where you don't need local caching
 */
abstract class NetworkOnlyResource<RequestType> {
    
    fun asFlow(): Flow<Result<RequestType>> = flow {
        emit(Result.Loading)
        
        try {
            val response = createCall()
            emit(Result.Success(response))
        } catch (exception: Exception) {
            Timber.e(exception, "Network call failed")
            emit(Result.Error(exception))
        }
    }
    
    protected abstract suspend fun createCall(): RequestType
}

/**
 * Simplified version for cache-only scenarios
 */
abstract class CacheOnlyResource<ResultType> {
    
    fun asFlow(): Flow<Result<ResultType>> = flow {
        emit(Result.Loading)
        
        try {
            emitAll(loadFromDb().map { data ->
                if (data != null) {
                    Result.Success(data)
                } else {
                    Result.Error(Exception("No cached data available"))
                }
            })
        } catch (exception: Exception) {
            Timber.e(exception, "Error loading from cache")
            emit(Result.Error(exception))
        }
    }
    
    protected abstract fun loadFromDb(): Flow<ResultType?>
}

/**
 * Extension function to easily create a NetworkBoundResource
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline shouldFetch: suspend (ResultType?) -> Boolean = { true },
    crossinline loadFromDb: () -> Flow<ResultType?>,
    crossinline createCall: suspend () -> RequestType,
    crossinline saveCallResult: suspend (RequestType) -> Unit,
    shouldEmitCachedData: Boolean = true
): Flow<Result<ResultType>> {
    return object : NetworkBoundResource<ResultType, RequestType>() {
        override suspend fun shouldFetch(data: ResultType?): Boolean = shouldFetch(data)
        override fun loadFromDb(): Flow<ResultType?> = loadFromDb()
        override suspend fun createCall(): RequestType = createCall()
        override suspend fun saveCallResult(response: RequestType) = saveCallResult(response)
        override fun shouldEmitCachedData(): Boolean = shouldEmitCachedData
    }.asFlow()
} 