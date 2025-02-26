import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version ("2.0.0")
}

android {
    namespace = "com.example.rameshrussellproject4"
    compileSdk = 35

    packaging{
        resources.excludes.add("META-INF/INDEX.LIST")
        resources.excludes.add("META-INF/io.netty.versions.properties")

    }


    defaultConfig {
        applicationId = "com.example.rameshrussellproject4"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding = true
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
    implementation (libs.async.http.client)
    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.recyclerview.selection)
    implementation (libs.glide)
    implementation (libs.gson)
    implementation(libs.kotlinx.serialization.json)
    annotationProcessor (libs.compiler)
    implementation(libs.androidx.annotation)
    implementation("com.codepath.libraries:asynchttpclient:2.2.0")
}