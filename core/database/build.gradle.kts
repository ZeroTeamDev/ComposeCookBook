plugins {
    alias(libs.plugins.common.kotlin.module.configs)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.base.project.core.database"
}

dependencies {
    implementation(project(":core:common"))
    
    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    
    // Serialization
    implementation(libs.kotlinx.serialization.json)
    
    // Date/Time
    implementation(libs.kotlinx.datetime)
    
    // Logging
    implementation(libs.timber)
    
    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.room.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
} 