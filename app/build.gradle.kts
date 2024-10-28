plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.navigationSafeArgs)
    id("com.google.devtools.ksp")
    kotlin("kapt") // Apply the Kapt plugin
}

android {
    namespace = "com.example.mvvmbasecode"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvvmbasecode"
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        buildConfig = true
        viewBinding = true
    }

    kapt {
        correctErrorTypes = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofitGsonConverter)
    implementation(libs.retrofitConverterScalars)
    implementation(libs.okhttpLoggingInterceptor)

    implementation(libs.play.services.location)
    implementation(libs.androidx.room.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.navigationRuntimeKTX)
    implementation(libs.navigationDragmentKTX)
    implementation(libs.navigationUiKTX)
    implementation(libs.hiltNavigationFragment)


    implementation(libs.androidx.multidex)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.junit)


}