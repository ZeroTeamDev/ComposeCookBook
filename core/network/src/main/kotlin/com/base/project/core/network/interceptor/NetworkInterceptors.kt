package com.base.project.core.network.interceptor

import com.base.project.core.common.Constants.Network.HEADER_AUTHORIZATION
import com.base.project.core.common.Constants.Network.HEADER_CONTENT_TYPE
import com.base.project.core.common.Constants.Network.HEADER_USER_AGENT
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

/**
 * Interceptor for adding authentication headers to requests
 */
class AuthInterceptor(
    private val tokenProvider: () -> String?
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        val token = tokenProvider()
        
        val authenticatedRequest = if (token != null) {
            originalRequest.newBuilder()
                .header(HEADER_AUTHORIZATION, "Bearer $token")
                .build()
        } else {
            originalRequest
        }
        
        return chain.proceed(authenticatedRequest)
    }
}

/**
 * Interceptor for adding common headers to all requests
 */
class HeaderInterceptor(
    private val userAgent: String = "BaseAndroidApp/1.0"
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        val requestWithHeaders = originalRequest.newBuilder()
            .header(HEADER_CONTENT_TYPE, "application/json")
            .header(HEADER_USER_AGENT, userAgent)
            .header("Accept", "application/json")
            .header("Cache-Control", "no-cache")
            .build()
        
        return chain.proceed(requestWithHeaders)
    }
}

/**
 * Interceptor for handling network errors and retries
 */
class ErrorHandlingInterceptor : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
        return try {
            val response = chain.proceed(request)
            
            // Log response status
            Timber.d("Response: ${response.code} ${response.message} for ${request.url}")
            
            // Handle specific error codes
            when (response.code) {
                401 -> {
                    Timber.w("Unauthorized request to ${request.url}")
                    // Could trigger token refresh here
                }
                403 -> {
                    Timber.w("Forbidden request to ${request.url}")
                }
                404 -> {
                    Timber.w("Not found: ${request.url}")
                }
                500 -> {
                    Timber.e("Server error for ${request.url}")
                }
            }
            
            response
        } catch (e: IOException) {
            Timber.e(e, "Network error for ${request.url}")
            throw e
        }
    }
}

/**
 * Interceptor for network connectivity checks
 */
class ConnectivityInterceptor(
    private val isNetworkAvailable: () -> Boolean
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            Timber.w("No network connection available")
            throw IOException("No network connection available")
        }
        
        return chain.proceed(chain.request())
    }
}

/**
 * Interceptor for request/response logging in debug builds
 */
class LoggingInterceptor : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.nanoTime()
        
        Timber.d("Sending request ${request.url} ${request.method}")
        
        val response = chain.proceed(request)
        
        val endTime = System.nanoTime()
        val duration = (endTime - startTime) / 1_000_000 // Convert to milliseconds
        
        Timber.d("Received response for ${request.url} in ${duration}ms")
        
        return response
    }
} 