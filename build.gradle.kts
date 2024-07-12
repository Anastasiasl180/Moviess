// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
 /*   id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
*/

    id("com.android.application") version "8.3.2" apply false
    id("com.android.library") version "8.3.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.22" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false

}

buildscript {
    dependencies {
        classpath ("com.android.tools.build:gradle:8.3.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
        classpath("com.google.gms:google-services:4.4.2")
        classpath("org.jetbrains.kotlin:kotlin-serialization:2.0.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
    }

}
