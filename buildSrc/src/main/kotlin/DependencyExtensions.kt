package com.base.project.build

import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Extension functions for easy dependency management
 * Usage: addCoreAndroidDependencies() in build.gradle.kts
 */

/**
 * Add all core Android dependencies that every module needs
 */
fun DependencyHandler.addCoreAndroidDependencies() {
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.activity)
    implementation(Dependencies.AndroidX.fragment)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)
    implementation(Dependencies.Utils.timber)
}

/**
 * Add Jetpack Compose dependencies
 */
fun DependencyHandler.addComposeDependencies() {
    // BOM will be added manually in build.gradle
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.foundationLayout)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.materialIconsExtended)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.viewModel)
    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.hiltNavigation)
    implementation(Dependencies.Compose.runtimeLiveData)
    
    debugImplementation(Dependencies.Compose.uiTooling)
    debugImplementation(Dependencies.Compose.uiTestManifest)
}

/**
 * Add Hilt dependency injection
 */
fun DependencyHandler.addHiltDependencies() {
    implementation(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.work)
    kapt(Dependencies.Hilt.compiler)
    kapt(Dependencies.Hilt.hiltCompiler)
}

/**
 * Add Room database dependencies
 */
fun DependencyHandler.addRoomDependencies() {
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    kapt(Dependencies.Room.compiler)
}

/**
 * Add DataStore dependencies
 */
fun DependencyHandler.addDataStoreDependencies() {
    implementation(Dependencies.DataStore.preferences)
    implementation(Dependencies.DataStore.core)
}

/**
 * Add network dependencies (Retrofit + OkHttp)
 */
fun DependencyHandler.addNetworkDependencies() {
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitGson)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.okHttpLogging)
    implementation(Dependencies.Network.gson)
}

/**
 * Add image loading dependencies
 */
fun DependencyHandler.addImageLoadingDependencies() {
    implementation(Dependencies.ImageLoading.coil)
    implementation(Dependencies.ImageLoading.coilCompose)
}

/**
 * Add UI libraries
 */
fun DependencyHandler.addUILibraries() {
    implementation(Dependencies.UI.material)
    implementation(Dependencies.UI.lottie)
    implementation(Dependencies.UI.shimmer)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.recyclerView)
    implementation(Dependencies.AndroidX.viewPager2)
}

/**
 * Add testing dependencies
 */
fun DependencyHandler.addTestingDependencies() {
    testImplementation(Dependencies.Testing.junit)
    testImplementation(Dependencies.Testing.mockk)
    testImplementation(Dependencies.Testing.truth)
    testImplementation(Dependencies.Testing.coroutinesTest)
    testImplementation(Dependencies.Testing.robolectric)
    testImplementation(Dependencies.Testing.turbine)
    
    androidTestImplementation(Dependencies.Testing.junitExt)
    androidTestImplementation(Dependencies.Testing.espressoCore)
    androidTestImplementation(Dependencies.Testing.composeUiTest)
    androidTestImplementation(Dependencies.Testing.hiltTesting)
    androidTestImplementation(Dependencies.Testing.mockkAndroid)
}

/**
 * Add security dependencies
 */
fun DependencyHandler.addSecurityDependencies() {
    implementation(Dependencies.AndroidX.crypto)
    implementation(Dependencies.AndroidX.biometric)
}

/**
 * Add performance dependencies
 */
fun DependencyHandler.addPerformanceDependencies() {
    implementation(Dependencies.AndroidX.startup)
    implementation(Dependencies.AndroidX.profileInstaller)
}

/**
 * Add Firebase dependencies (optional)
 */
fun DependencyHandler.addFirebaseDependencies() {
    // BOM will be added manually in build.gradle
    implementation(Dependencies.Firebase.analytics)
    implementation(Dependencies.Firebase.crashlytics)
    implementation(Dependencies.Firebase.messaging)
}

/**
 * Add Play Services dependencies
 */
fun DependencyHandler.addPlayServicesDependencies() {
    implementation(Dependencies.PlayServices.location)
    implementation(Dependencies.PlayServices.maps)
}

/**
 * Add CameraX dependencies
 */
fun DependencyHandler.addCameraXDependencies() {
    implementation(Dependencies.CameraX.core)
    implementation(Dependencies.CameraX.camera2)
    implementation(Dependencies.CameraX.lifecycle)
    implementation(Dependencies.CameraX.view)
    implementation(Dependencies.CameraX.extensions)
}

/**
 * Add Work Manager dependencies
 */
fun DependencyHandler.addWorkManagerDependencies() {
    implementation(Dependencies.AndroidX.workManager)
}

/**
 * Add debug tools
 */
fun DependencyHandler.addDebugTools() {
    debugImplementation(Dependencies.Debug.leakCanary)
}

/**
 * Add all essential dependencies for a basic Android app
 */
fun DependencyHandler.addEssentialDependencies() {
    addCoreAndroidDependencies()
    addComposeDependencies()
    addHiltDependencies()
    addImageLoadingDependencies()
    addTestingDependencies()
    addDebugTools()
}

/**
 * Add all dependencies for a data layer module
 */
fun DependencyHandler.addDataLayerDependencies() {
    addCoreAndroidDependencies()
    addHiltDependencies()
    addRoomDependencies()
    addDataStoreDependencies()
    addNetworkDependencies()
    addTestingDependencies()
}

/**
 * Add all dependencies for a feature module
 */
fun DependencyHandler.addFeatureModuleDependencies() {
    addCoreAndroidDependencies()
    addComposeDependencies()
    addHiltDependencies()
    addImageLoadingDependencies()
    addTestingDependencies()
}

// Extension functions to make dependency declaration cleaner
private fun DependencyHandler.implementation(dependencyNotation: String) {
    add("implementation", dependencyNotation)
}

private fun DependencyHandler.testImplementation(dependencyNotation: String) {
    add("testImplementation", dependencyNotation)
}

private fun DependencyHandler.androidTestImplementation(dependencyNotation: String) {
    add("androidTestImplementation", dependencyNotation)
}

private fun DependencyHandler.debugImplementation(dependencyNotation: String) {
    add("debugImplementation", dependencyNotation)
}

private fun DependencyHandler.kapt(dependencyNotation: String) {
    add("kapt", dependencyNotation)
}

// Remove platform function - will be handled differently 