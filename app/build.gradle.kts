plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = "com.beatriz.eventos"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
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
    viewBinding {
        android.buildFeatures.viewBinding = true
        android.buildFeatures.dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    //app libs
    implementation(AppDependencies.kotlinStdLib)
    implementation(AppDependencies.kotlinStd)
    implementation(AppDependencies.coreKtx)
    implementation(AppDependencies.constraintLayout)
    implementation(AppDependencies.appcompat)
    implementation(AppDependencies.material)
    implementation(AppDependencies.navigationFragmentKtx)
    implementation(AppDependencies.navigationUiKtx)
    implementation(AppDependencies.lifecycleLiveDataKtx)
    implementation(AppDependencies.lifecycleExtensions)
    implementation(AppDependencies.lifecycleViewmodel)
    implementation(AppDependencies.koinAndroid)
    implementation(AppDependencies.koinAndroidCompat)
    implementation(AppDependencies.koinAndroidWorkNavigation)
    implementation(AppDependencies.koinAndroidWorkmanager)
    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.okHttp)
    implementation(AppDependencies.gsonConverter)
    implementation(AppDependencies.gson)
    implementation(AppDependencies.roundedImageView)
    implementation(AppDependencies.lottie)
    implementation(AppDependencies.skeleton)

    //test libs
    testImplementation(AppDependencies.junit)
    androidTestImplementation(AppDependencies.extJUnit)
    androidTestImplementation(AppDependencies.espressoCore)

}
