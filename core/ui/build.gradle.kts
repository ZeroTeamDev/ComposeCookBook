plugins {
    alias(libs.plugins.common.compose.module.configs)
}

android {
    namespace = "com.base.project.core.ui"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:design-system"))
    
    // Compose BOM
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    
    // Activity & ViewModel
    implementation(libs.activity.compose)
    implementation(libs.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    
    // Navigation
    implementation(libs.navigation.compose)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    
    // Permissions
    implementation(libs.accompanist.permissions)
    
    // System UI Controller
    implementation(libs.accompanist.systemuicontroller)
    
    // Logging
    implementation(libs.timber)
    
    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.compose.ui.test.junit4)
    
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
} 