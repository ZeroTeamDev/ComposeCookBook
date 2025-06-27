package com.base.project.build

/**
 * Base Project Configuration
 * Central configuration for all Android base projects
 */
object ProjectConfig {
    // App Configuration
    const val COMPILE_SDK = 34
    const val MIN_SDK = 24  // Support 95%+ devices
    const val TARGET_SDK = 34
    
    // Base Application ID - will be overridden in specific projects
    const val APPLICATION_ID = "com.base.project"
    
    // Versioning
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    
    // Build Configuration
    const val BUILD_TOOLS_VERSION = "34.0.0"
    const val NDK_VERSION = "25.1.8937393"
    
    // Java/Kotlin Configuration
    const val JAVA_VERSION = "17"
    const val JVM_TARGET = "17"
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.8"
    
    // Test Configuration
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    
    // Proguard
    const val PROGUARD_ANDROID_OPTIMIZE = "proguard-android-optimize.txt"
    const val PROGUARD_RULES = "proguard-rules.pro"
    
    // Feature flags
    const val ENABLE_COMPOSE = true
    const val ENABLE_MULTIDEX = true
    const val ENABLE_VIEW_BINDING = false  // Use Compose instead
    const val ENABLE_DATA_BINDING = false  // Use Compose instead
} 