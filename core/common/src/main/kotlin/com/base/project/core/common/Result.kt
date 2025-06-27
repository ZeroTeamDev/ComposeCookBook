package com.base.project.core.common

/**
 * A generic class that holds a value or an exception
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

/**
 * Extension function to check if result is success
 */
fun <T> Result<T>.isSuccess(): Boolean = this is Result.Success

/**
 * Extension function to check if result is error
 */
fun <T> Result<T>.isError(): Boolean = this is Result.Error

/**
 * Extension function to check if result is loading
 */
fun <T> Result<T>.isLoading(): Boolean = this is Result.Loading

/**
 * Extension function to get data from success result
 */
fun <T> Result<T>.getDataOrNull(): T? = when (this) {
    is Result.Success -> data
    else -> null
}

/**
 * Extension function to get error from error result
 */
fun <T> Result<T>.getErrorOrNull(): Throwable? = when (this) {
    is Result.Error -> exception
    else -> null
}

/**
 * Extension function to execute action on success
 */
inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

/**
 * Extension function to execute action on error
 */
inline fun <T> Result<T>.onError(action: (exception: Throwable) -> Unit): Result<T> {
    if (this is Result.Error) action(exception)
    return this
}

/**
 * Extension function to execute action on loading
 */
inline fun <T> Result<T>.onLoading(action: () -> Unit): Result<T> {
    if (this is Result.Loading) action()
    return this
}

/**
 * Extension function to map result data
 */
inline fun <T, R> Result<T>.map(transform: (value: T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Error -> Result.Error(exception)
        is Result.Loading -> Result.Loading
    }
}

/**
 * Extension function to flatMap result
 */
inline fun <T, R> Result<T>.flatMap(transform: (value: T) -> Result<R>): Result<R> {
    return when (this) {
        is Result.Success -> transform(data)
        is Result.Error -> Result.Error(exception)
        is Result.Loading -> Result.Loading
    }
} 