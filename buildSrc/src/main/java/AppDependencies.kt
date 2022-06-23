object AppDependencies {
    //std lib
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //android ui
    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStd}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val lifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLiveDataKtx}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val lifecycleViewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}"
    const val navigationUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationUiKtx}"

    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val kotlinPlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // External
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
    const val koinAndroidWorkmanager =
        "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
    const val koinAndroidWorkNavigation =
        "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val roundedImageView =
        "com.makeramen:roundedimageview:${Versions.roundedImageView}"
    const val lottie =
        "com.airbnb.android:lottie:${Versions.lottie}"

    //test libs
    const val junit = "junit:junit:${Versions.junit}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
