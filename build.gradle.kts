buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
    repositories {
        google()
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
}