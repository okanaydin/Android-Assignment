plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {

    /**
     * Starting in November 2021, app updates will be required to target API level 30 or above and adjust for behavioral changes in Android 11.
     * Existing apps that are not receiving updates are unaffected and can continue to be downloaded from the Play Store.
     * https://developer.android.com/distribute/best-practices/develop/target-sdk
     */

    compileSdkVersion(Configs.compileSdkVersion)
    defaultConfig {
        applicationId(Configs.applicationId)
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        versionCode(Configs.versionCode)
        versionName(Configs.versionName)
        testInstrumentationRunner(Configs.testInstrumentationRunner)
    }

    buildTypes {
        getByName("release") {
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.cardView)
    implementation(Dependencies.daggerHilt)
    implementation(Dependencies.glide)
    implementation(Dependencies.gson) // TODO #1 this will be removed, will use moshi instead.
    implementation(Dependencies.liveDataKtx)
    implementation(Dependencies.moshi)
    implementation(Dependencies.okhttp3)
    implementation(Dependencies.okhttp3Interceptor)
    implementation(Dependencies.retrofit2)
    implementation(Dependencies.retrofit2MoshiConvertor)
    implementation(Dependencies.viewModelKtx)

    kapt(Dependencies.daggerHiltCompiler)
    kapt(Dependencies.moshiCodegen)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.jUnitExt)
    androidTestImplementation(Dependencies.espresso)
}
