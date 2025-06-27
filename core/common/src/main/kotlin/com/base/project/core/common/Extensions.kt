package com.base.project.core.common

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.text.SimpleDateFormat
import java.util.*

/**
 * Context Extensions
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

/**
 * String Extensions
 */
fun String?.isNotNullOrEmpty(): Boolean = !this.isNullOrEmpty()

fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

fun String.capitalizeFirstLetter(): String {
    return if (isEmpty()) this else this[0].uppercase() + substring(1).lowercase()
}

/**
 * Date Extensions
 */
fun Long.toDateString(pattern: String = Constants.DATE_FORMAT_DEFAULT): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(this))
}

fun String.toDate(pattern: String = Constants.DATE_FORMAT_DEFAULT): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}

/**
 * Collection Extensions
 */
fun <T> List<T>?.isNotNullOrEmpty(): Boolean = !this.isNullOrEmpty()

fun <T> List<T>.second(): T {
    if (size < 2) throw NoSuchElementException("List has less than 2 elements")
    return this[1]
}

fun <T> List<T>.secondOrNull(): T? = if (size >= 2) this[1] else null

/**
 * Flow Extensions
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it)) }
}

/**
 * LiveData Extensions
 */
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }
    })
}

/**
 * Number Extensions
 */
fun Int.isEven(): Boolean = this % 2 == 0

fun Int.isOdd(): Boolean = this % 2 != 0

fun Double.formatToTwoDecimals(): String = String.format("%.2f", this)

fun Float.formatToTwoDecimals(): String = String.format("%.2f", this)

/**
 * Boolean Extensions
 */
fun Boolean.toInt(): Int = if (this) 1 else 0

/**
 * Safe casting extensions
 */
inline fun <reified T> Any?.safeCast(): T? = this as? T

/**
 * Lazy extensions
 */
fun <T> lazyUnsafe(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer) 