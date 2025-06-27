plugins {
    alias(libs.plugins.common.kotlin.module.configs)
}

android {
    namespace = "com.base.project.core.network"
}

dependencies {
    implementation(project(":core:common"))
    
    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    
    // Serialization
    implementation(libs.kotlinx.serialization.json)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    
    // Logging
    implementation(libs.timber)
    
    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.okhttp.mockwebserver)
} 