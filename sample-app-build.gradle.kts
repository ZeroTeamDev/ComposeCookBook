import com.base.project.build.ProjectConfig
import com.base.project.build.Dependencies
import com.base.project.build.addEssentialDependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.base.project.app"
    compileSdk = ProjectConfig.COMPILE_SDK

    defaultConfig {
        applicationId = ProjectConfig.APPLICATION_ID
        minSdk = ProjectConfig.MIN_SDK
        targetSdk = ProjectConfig.TARGET_SDK
        versionCode = ProjectConfig.VERSION_CODE
        versionName = ProjectConfig.VERSION_NAME

        testInstrumentationRunner = ProjectConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
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
    
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.KOTLIN_COMPILER_EXTENSION_VERSION
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core modules
    implementation(project(":core:common"))
    implementation(project(":core:design-system"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    
    // Feature modules
    implementation(project(":features:auth"))
    implementation(project(":features:profile"))
    implementation(project(":features:settings"))
    
    // Essential dependencies (Compose, Hilt, etc.)
    addEssentialDependencies()
    
    // Compose BOM (must be added manually)
    implementation(platform(Dependencies.Compose.bom))
    
    // Firebase (optional)
    // implementation(platform(Dependencies.Firebase.bom))
    // addFirebaseDependencies()
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
} 