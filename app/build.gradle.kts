plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.plugin.compose")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.moviess"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviess"
        minSdk = 26
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")


    implementation("androidx.compose.material3.adaptive:adaptive-android:1.0.0-beta04")//

    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.compose.material3:material3-window-size-class:1.2.1")
        //implementation ("androidx.compose.material3:material3-adaptive:1.0.0-alpha06")
    implementation ("androidx.compose.material3:material3-adaptive-navigation-suite:1.0.0-alpha07")


    implementation("androidx.compose.ui:ui-tooling:1.7.0-beta05")
    implementation("androidx.compose.material:material:1.7.0-beta05")
    implementation("androidx.compose.animation:animation:1.7.0-beta05")
    implementation("androidx.compose.runtime:runtime:1.7.0-beta05")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.appcompat:appcompat")


    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("com.github.CanHub:Android-Image-Cropper:4.0.0")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")
    implementation("androidx.compose.foundation:foundation:1.6.8")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation("com.exyte:animated-navigation-bar:1.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-storage:21.0.0")
    implementation("com.google.firebase:firebase-database:21.0.0")
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))

    //Progress Dialog Library
    implementation("com.jpardogo.googleprogressbar:library:1.2.0")
    //Circular Image Library


    val nav_version = "2.7.7"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha13")
    // To use constraintlayout in compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")


    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //Dagger-Hilt
    val daggerVersion = "2.51"
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    ksp("com.google.dagger:hilt-android-compiler:$daggerVersion")

    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    val navigationDaggerVersion = "1.2.0"
    implementation("androidx.hilt:hilt-navigation-compose:$navigationDaggerVersion")
    ksp("androidx.hilt:hilt-compiler:$navigationDaggerVersion")
}