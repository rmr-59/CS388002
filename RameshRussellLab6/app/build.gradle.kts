import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-parcelize")
    id ("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}
val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties =  Properties()
apikeyProperties.load( FileInputStream(apikeyPropertiesFile))
android.buildFeatures.buildConfig=true
android {
    namespace = "com.example.rameshrusselllab6"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rameshrusselllab6"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", apikeyProperties["API_KEY"].toString())
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
    buildFeatures{
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
    implementation (libs.kotlin.stdlib)
    implementation (libs.androidx.core.ktx.v180)
    implementation (libs.androidx.appcompat.v142)
    implementation (libs.material.v161)
    implementation (libs.androidx.constraintlayout.v221)
    implementation ("com.codepath.libraries:asynchttpclient:2.2.0")
    implementation ("androidx.recyclerview:recyclerview:1.4.0")
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.code.gson:gson:2.12.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
    androidTestImplementation ( "androidx.test.ext:junit:1.1.3" )
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}