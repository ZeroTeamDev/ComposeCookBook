import com.base.project.build.ProjectConfig
import com.base.project.build.addCoreAndroidDependencies
import com.base.project.build.addComposeDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.base.project.core.design"
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
    }
    
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.KOTLIN_COMPILER_EXTENSION_VERSION
    }
}

dependencies {
    addCoreAndroidDependencies()
    addComposeDependencies()
    
    implementation(project(":core:common"))
} 