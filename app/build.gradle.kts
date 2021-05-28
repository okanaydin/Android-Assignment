plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

android {

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

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {

    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.coil)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.cardView)
    implementation(Dependencies.daggerHilt)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.liveDataKtx)
    implementation(Dependencies.moshi)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.okhttp3)
    implementation(Dependencies.okhttp3Interceptor)
    implementation(Dependencies.retrofit2)
    implementation(Dependencies.retrofit2MoshiConvertor)
    implementation(Dependencies.swipeRefreshLayout)
    implementation(Dependencies.viewModelKtx)

    kapt(Dependencies.daggerHiltCompiler)
    kapt(Dependencies.moshiCodegen)

    testImplementation(Dependencies.archTest)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.truthExtensions)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.robolectric)

    testImplementation(Dependencies.jupiterApi)
    testRuntimeOnly(Dependencies.jupiterEngine)
    testImplementation(Dependencies.jupiterParams)
    testRuntimeOnly(Dependencies.jupiterVintageEngine)

    androidTestImplementation(Dependencies.jUnitExt)
    androidTestImplementation(Dependencies.espresso)
}
