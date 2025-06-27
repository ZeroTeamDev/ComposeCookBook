package com.base.project.core.network.model

import kotlinx.serialization.Serializable

/**
 * Standard API response wrapper
 * Provides consistent structure for all API responses
 */
@Serializable
data class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val success: Boolean = true,
    val errorCode: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * API Error response
 */
@Serializable
data class ApiError(
    val code: String,
    val message: String,
    val details: Map<String, String>? = null,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Pagination metadata for list responses
 */
@Serializable
data class PaginationInfo(
    val page: Int,
    val pageSize: Int,
    val totalItems: Long,
    val totalPages: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean
)

/**
 * Paginated API response
 */
@Serializable
data class PaginatedResponse<T>(
    val items: List<T>,
    val pagination: PaginationInfo
) 