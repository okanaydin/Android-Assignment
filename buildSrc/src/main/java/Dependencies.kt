object Dependencies {
    /**
     * AndroidX replaces the original support library APIs with packages in the androidx namespace.
     * Only the package and Maven artifact names changed; class, method, and field names did not change.
     * ref: https://developer.android.com/jetpack/androidx/migrate
     */

    const val androidMaterial =
        "com.google.android.material:material:${Versions.androidMaterialVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHiltVersion}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHiltVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlintVersion}"
    const val jUnit = "junit:junit:${Versions.junitVersion}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.junitExtensionsVersion}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
}
