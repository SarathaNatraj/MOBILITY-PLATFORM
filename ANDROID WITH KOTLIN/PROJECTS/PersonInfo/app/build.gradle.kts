plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.personinfo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.personinfo"
        minSdk = 24

        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding{
        enable=true
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.androidx.core.ktx) // Android core KTX extensions
    implementation(libs.androidx.appcompat) // AppCompat support library
    implementation(libs.material) // Material Design components
    implementation(libs.androidx.constraintlayout) // ConstraintLayout library

    // Activity KTX for lifecycle and components
    implementation(libs.androidx.activity)

    // Testing dependencies
    testImplementation(libs.junit) // JUnit for unit testing
    androidTestImplementation(libs.androidx.junit) // AndroidX JUnit for Android tests
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI testing

    // Retrofit for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

    // Android Navigation components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.2") // Updated to latest stable
    implementation("androidx.navigation:navigation-ui-ktx:2.7.2") // Updated to latest stable

    // Lottie for animations
    implementation("com.airbnb.android:lottie:6.1.0") // Revert to the latest stable version

    // Optional: Add a coroutine dependency if you use coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Latest stable coroutine library
}
