package com.base.project.core.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Base DAO interface providing common CRUD operations
 */
@Dao
interface BaseDao<T> {
    
    /**
     * Insert a single item
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T): Long
    
    /**
     * Insert multiple items
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<T>): List<Long>
    
    /**
     * Update an item
     */
    @Update
    suspend fun update(item: T): Int
    
    /**
     * Update multiple items
     */
    @Update
    suspend fun updateAll(items: List<T>): Int
    
    /**
     * Delete an item
     */
    @Delete
    suspend fun delete(item: T): Int
    
    /**
     * Delete multiple items
     */
    @Delete
    suspend fun deleteAll(items: List<T>): Int
    
    /**
     * Upsert (Insert or Update) a single item
     */
    @Transaction
    suspend fun upsert(item: T) {
        val result = insert(item)
        if (result == -1L) {
            update(item)
        }
    }
    
    /**
     * Upsert (Insert or Update) multiple items
     */
    @Transaction
    suspend fun upsertAll(items: List<T>) {
        items.forEach { upsert(it) }
    }
}

/**
 * Extended base DAO with common query methods
 */
@Dao
abstract class ExtendedBaseDao<T> : BaseDao<T> {
    
    /**
     * Clear all items from the table
     * Must be implemented by subclasses with @Query("DELETE FROM table_name")
     */
    @Transaction
    abstract suspend fun clearAll()
    
    /**
     * Get count of items in the table
     * Must be implemented by subclasses with @Query("SELECT COUNT(*) FROM table_name")
     */
    abstract suspend fun getCount(): Int
    
    /**
     * Check if table is empty
     */
    suspend fun isEmpty(): Boolean = getCount() == 0
    
    /**
     * Check if table has data
     */
    suspend fun hasData(): Boolean = getCount() > 0
    
    /**
     * Replace all data (clear and insert new)
     */
    @Transaction
    open suspend fun replaceAll(items: List<T>) {
        clearAll()
        insertAll(items)
    }
}

/**
 * Paginated DAO interface
 */
interface PaginatedDao<T> {
    
    /**
     * Get items with offset and limit
     */
    suspend fun getItemsPaginated(offset: Int, limit: Int): List<T>
    
    /**
     * Get items with offset and limit as Flow
     */
    fun getItemsPaginatedFlow(offset: Int, limit: Int): Flow<List<T>>
}

/**
 * Searchable DAO interface
 */
interface SearchableDao<T> {
    
    /**
     * Search items by query
     */
    suspend fun searchItems(query: String): List<T>
    
    /**
     * Search items by query as Flow
     */
    fun searchItemsFlow(query: String): Flow<List<T>>
}

/**
 * Time-based DAO interface for entities with timestamps
 */
interface TimestampedDao<T> {
    
    /**
     * Get items newer than timestamp
     */
    suspend fun getItemsNewerThan(timestamp: Long): List<T>
    
    /**
     * Get items older than timestamp
     */
    suspend fun getItemsOlderThan(timestamp: Long): List<T>
    
    /**
     * Get items between two timestamps
     */
    suspend fun getItemsBetween(startTimestamp: Long, endTimestamp: Long): List<T>
    
    /**
     * Delete items older than timestamp
     */
    suspend fun deleteItemsOlderThan(timestamp: Long): Int
    
    /**
     * Get latest items
     */
    suspend fun getLatestItems(limit: Int): List<T>
}

/**
 * Base entity interface for common fields
 */
interface BaseEntity {
    val id: Long
    val createdAt: Long
    val updatedAt: Long
}

/**
 * Soft delete entity interface
 */
interface SoftDeletableEntity : BaseEntity {
    val isDeleted: Boolean
    val deletedAt: Long?
} 