package com.base.project.build

/**
 * Dependencies and Versions for Base Project
 * All commonly used libraries with their versions
 */
object Dependencies {
    
    // Versions
    object Versions {
        // Android & Kotlin
        const val kotlin = "1.9.22"
        const val androidGradlePlugin = "8.2.2"
        const val ksp = "1.9.22-1.0.17"
        
        // AndroidX Core
        const val androidxCore = "1.12.0"
        const val androidxLifecycle = "2.7.0"
        const val androidxActivity = "1.8.2"
        const val androidxFragment = "1.6.2"
        const val androidxAppCompat = "1.6.1"
        const val androidxConstraintLayout = "2.1.4"
        const val androidxRecyclerView = "1.3.2"
        
        // Jetpack Compose
        const val composeBom = "2024.02.00"
        const val composeCompiler = "1.5.8"
        const val composeNavigation = "2.7.6"
        const val composeHiltNavigation = "1.1.0"
        
        // Material Design
        const val material = "1.11.0"
        const val materialCompose = "1.5.4"
        
        // Architecture Components
        const val viewModel = "2.7.0"
        const val liveData = "2.7.0"
        const val room = "2.6.1"
        const val dataStore = "1.0.0"
        
        // Dependency Injection
        const val hilt = "2.48.1"
        const val hiltCompiler = "1.1.0"
        
        // Network
        const val retrofit = "2.9.0"
        const val okHttp = "4.12.0"
        const val gson = "2.10.1"
        const val moshi = "1.15.0"
        
        // Coroutines
        const val coroutines = "1.7.3"
        
        // Image Loading
        const val coil = "2.5.0"
        const val glide = "4.16.0"
        
        // UI Libraries
        const val lottie = "6.3.0"
        const val shimmer = "0.5.0"
        const val viewPager2 = "1.0.0"
        
        // Utility Libraries
        const val timber = "5.0.1"
        const val leakCanary = "2.12"
        
        // Testing
        const val junit = "4.13.2"
        const val junitExt = "1.1.5"
        const val espresso = "3.5.1"
        const val mockk = "1.13.8"
        const val truth = "1.1.4"
        const val turbine = "1.0.0"
        const val robolectric = "4.11.1"
        
        // Security
        const val crypto = "1.1.0-alpha06"
        const val biometric = "1.1.0"
        
        // Performance
        const val startup = "1.1.1"
        const val profileInstaller = "1.3.1"
        
        // Firebase (optional)
        const val firebaseBom = "32.7.2"
        
        // Play Services
        const val playServicesLocation = "21.0.1"
        const val playServicesMaps = "18.2.0"
        
        // Camera
        const val cameraX = "1.3.1"
        
        // Work Manager
        const val workManager = "2.9.0"
    }
    
    // AndroidX Core Libraries
    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
        const val activity = "androidx.activity:activity-ktx:${Versions.androidxActivity}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerView}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
        const val startup = "androidx.startup:startup-runtime:${Versions.startup}"
        const val profileInstaller = "androidx.profileinstaller:profileinstaller:${Versions.profileInstaller}"
        const val crypto = "androidx.security:security-crypto:${Versions.crypto}"
        const val biometric = "androidx.biometric:biometric:${Versions.biometric}"
        const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    }
    
    // Jetpack Compose
    object Compose {
        const val bom = "androidx.compose:compose-bom:${Versions.composeBom}"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
        const val runtime = "androidx.compose.runtime:runtime"
        const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata"
        const val foundation = "androidx.compose.foundation:foundation"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout"
        const val material = "androidx.compose.material:material"
        const val material3 = "androidx.compose.material3:material3"
        const val materialIconsCore = "androidx.compose.material:material-icons-core"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended"
        const val activity = "androidx.activity:activity-compose:${Versions.androidxActivity}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModel}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.composeHiltNavigation}"
    }
    
    // Room Database
    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val testing = "androidx.room:room-testing:${Versions.room}"
    }
    
    // DataStore
    object DataStore {
        const val preferences = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
        const val core = "androidx.datastore:datastore-core:${Versions.dataStore}"
    }
    
    // Dependency Injection
    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val testing = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
        const val work = "androidx.hilt:hilt-work:${Versions.hiltCompiler}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    }
    
    // Networking
    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }
    
    // Coroutines
    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }
    
    // Image Loading
    object ImageLoading {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }
    
    // UI Libraries
    object UI {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    }
    
    // Utility Libraries
    object Utils {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }
    
    // Debug Tools
    object Debug {
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    }
    
    // Testing
    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
        const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
        const val truth = "com.google.truth:truth:${Versions.truth}"
        const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val coroutinesTest = Coroutines.test
        const val composeUiTest = Compose.uiTestJunit4
        const val hiltTesting = Hilt.testing
    }
    
    // Firebase (Optional)
    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val messaging = "com.google.firebase:firebase-messaging-ktx"
        const val auth = "com.google.firebase:firebase-auth-ktx"
        const val firestore = "com.google.firebase:firebase-firestore-ktx"
        const val storage = "com.google.firebase:firebase-storage-ktx"
    }
    
    // Play Services
    object PlayServices {
        const val location = "com.google.android.gms:play-services-location:${Versions.playServicesLocation}"
        const val maps = "com.google.android.gms:play-services-maps:${Versions.playServicesMaps}"
    }
    
    // Camera
    object CameraX {
        const val core = "androidx.camera:camera-core:${Versions.cameraX}"
        const val camera2 = "androidx.camera:camera-camera2:${Versions.cameraX}"
        const val lifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
        const val video = "androidx.camera:camera-video:${Versions.cameraX}"
        const val view = "androidx.camera:camera-view:${Versions.cameraX}"
        const val extensions = "androidx.camera:camera-extensions:${Versions.cameraX}"
    }
} 