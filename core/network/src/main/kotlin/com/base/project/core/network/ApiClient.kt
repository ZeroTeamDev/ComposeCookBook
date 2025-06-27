package com.base.project.core.network

import com.base.project.core.common.Constants.Network.DEFAULT_TIMEOUT
import com.base.project.core.network.interceptor.AuthInterceptor
import com.base.project.core.network.interceptor.ConnectivityInterceptor
import com.base.project.core.network.interceptor.ErrorHandlingInterceptor
import com.base.project.core.network.interceptor.HeaderInterceptor
import com.base.project.core.network.interceptor.LoggingInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Network client configuration and factory
 * Provides configured Retrofit instances for API communication
 */
class ApiClient private constructor() {
    
    companion object {
        @Volatile
        private var INSTANCE: ApiClient? = null
        
        fun getInstance(): ApiClient {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ApiClient().also { INSTANCE = it }
            }
        }
    }
    
    // JSON configuration for serialization
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
        isLenient = true
    }
    
    /**
     * Creates a configured OkHttpClient
     */
    fun createOkHttpClient(
        isDebug: Boolean = false,
        tokenProvider: (() -> String?)? = null,
        isNetworkAvailable: (() -> Boolean)? = null
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                // Add header interceptor
                addInterceptor(HeaderInterceptor())
                
                // Add auth interceptor if token provider is available
                tokenProvider?.let { provider ->
                    addInterceptor(AuthInterceptor(provider))
                }
                
                // Add connectivity interceptor if network checker is available
                isNetworkAvailable?.let { checker ->
                    addInterceptor(ConnectivityInterceptor(checker))
                }
                
                // Add error handling interceptor
                addInterceptor(ErrorHandlingInterceptor())
                
                // Add logging interceptors for debug builds
                if (isDebug) {
                    addInterceptor(LoggingInterceptor())
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }
            .build()
    }
    
    /**
     * Creates a configured Retrofit instance
     */
    fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient = createOkHttpClient()
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }
    
    /**
     * Creates an API service instance
     */
    inline fun <reified T> createApiService(
        baseUrl: String,
        okHttpClient: OkHttpClient = createOkHttpClient()
    ): T {
        return createRetrofit(baseUrl, okHttpClient).create(T::class.java)
    }
    
    /**
     * Creates an API service with custom configuration
     */
    inline fun <reified T> createApiService(
        baseUrl: String,
        isDebug: Boolean = false,
        tokenProvider: (() -> String?)? = null,
        isNetworkAvailable: (() -> Boolean)? = null
    ): T {
        val okHttpClient = createOkHttpClient(
            isDebug = isDebug,
            tokenProvider = tokenProvider,
            isNetworkAvailable = isNetworkAvailable
        )
        return createApiService<T>(baseUrl, okHttpClient)
    }
}

/**
 * Extension function to easily create API services
 */
inline fun <reified T> createApiService(
    baseUrl: String,
    isDebug: Boolean = false,
    tokenProvider: (() -> String?)? = null,
    isNetworkAvailable: (() -> Boolean)? = null
): T {
    return ApiClient.getInstance().createApiService<T>(
        baseUrl = baseUrl,
        isDebug = isDebug,
        tokenProvider = tokenProvider,
        isNetworkAvailable = isNetworkAvailable
    )
} 