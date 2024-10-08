plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltKotlinAndroid)
    alias(libs.plugins.ksp)
    kotlin("kapt")
}

android {
    namespace = "com.example.mynewsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mynewsapp"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //NAVIGATION
    implementation(libs.androidx.navigation)

    //Retrofit
    implementation(libs.retrofit)

    //HttpLoggingInterceptor
    implementation(libs.okhttp3)

    //GSON
    implementation(libs.gsonConverter)

    // Coroutines Kotlin android
    implementation(libs.kotlinx.coroutines.android)

    // BottomNavigation
    implementation(libs.material)

    //Hilt
    implementation(libs.hilt)
    implementation(libs.hiltNavigation)
    kapt(libs.kapt)

    //Coil
    implementation(libs.coil)

    //Room DB
    implementation(libs.room)
    ksp(libs.kaptRoom)
    implementation(libs.roomCoroutine)

    // Material icons new
    implementation(libs.icons)


}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}


