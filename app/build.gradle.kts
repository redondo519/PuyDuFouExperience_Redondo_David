plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.navigation.safeargs)



}

android {
    namespace = "com.redondo.puydufouexperience"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.redondo.puydufouexperience"
        minSdk = 24
        targetSdk = 36
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

    // BINDING
    buildFeatures {
        viewBinding = true
    }
    

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    // Material Design (BottomNavigationView)
    implementation("com.google.android.material:material:1.11.0")

    // Room runtime
    implementation("androidx.room:room-runtime:2.6.1")

    // Kotlin Extensions and Coroutines support
    implementation("androidx.room:room-ktx:2.6.1")

    // For annotation processing (KAPT)
    kapt("androidx.room:room-compiler:2.6.1")

    // LiveData + ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //Maps
    implementation("com.google.android.gms:play-services-maps:18.2.0")

}