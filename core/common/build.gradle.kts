import com.base.project.build.ProjectConfig
import com.base.project.build.addCoreAndroidDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.base.project.core.common"
    compileSdk = ProjectConfig.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectConfig.MIN_SDK
        targetSdk = ProjectConfig.TARGET_SDK

        testInstrumentationRunner = ProjectConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ProjectConfig.PROGUARD_ANDROID_OPTIMIZE),
                ProjectConfig.PROGUARD_RULES
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = ProjectConfig.JVM_TARGET
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    addCoreAndroidDependencies()
    
    // Additional common utilities
    implementation("javax.inject:javax.inject:1")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
} 