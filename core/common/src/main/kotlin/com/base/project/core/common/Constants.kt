package com.base.project.core.common

/**
 * Application-wide constants
 */
object Constants {
    
    // Network
    const val NETWORK_TIMEOUT = 30L
    const val CACHE_SIZE = 10 * 1024 * 1024L // 10MB
    
    // Database
    const val DATABASE_NAME = "app_database"
    const val DATABASE_VERSION = 1
    
    // Preferences
    const val PREFERENCES_NAME = "app_preferences"
    
    // Date & Time
    const val DATE_FORMAT_DEFAULT = "yyyy-MM-dd"
    const val TIME_FORMAT_DEFAULT = "HH:mm:ss"
    const val DATETIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss"
    
    // UI
    const val ANIMATION_DURATION_SHORT = 200L
    const val ANIMATION_DURATION_MEDIUM = 400L
    const val ANIMATION_DURATION_LONG = 600L
    
    // Pagination
    const val PAGE_SIZE = 20
    const val INITIAL_LOAD_SIZE = 40
    
    // Image Loading
    const val IMAGE_CACHE_SIZE = 50 * 1024 * 1024L // 50MB
    const val IMAGE_PLACEHOLDER_SIZE = 200
    
    // Validation
    const val MIN_PASSWORD_LENGTH = 6
    const val MAX_PASSWORD_LENGTH = 50
    const val MAX_USERNAME_LENGTH = 30
    
    // Intent Extra Keys
    object IntentKeys {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_URL = "extra_url"
    }
    
    // Bundle Keys
    object BundleKeys {
        const val KEY_ID = "key_id"
        const val KEY_DATA = "key_data"
        const val KEY_POSITION = "key_position"
    }
    
    // Request Codes
    object RequestCodes {
        const val CAMERA_PERMISSION = 1001
        const val LOCATION_PERMISSION = 1002
        const val STORAGE_PERMISSION = 1003
        const val NOTIFICATION_PERMISSION = 1004
    }
    
    // Notification
    object Notifications {
        const val CHANNEL_ID_DEFAULT = "default_channel"
        const val CHANNEL_ID_IMPORTANT = "important_channel"
        const val NOTIFICATION_ID_DEFAULT = 1001
    }
} 