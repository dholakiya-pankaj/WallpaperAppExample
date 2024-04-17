plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.wallpaperapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wallpaperapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        //multiDexEnabled = true
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
    buildTypes.configureEach {
        this.buildConfigField("String", "UNSPLASH_BASE_URL", "\"https://api.unsplash.com/\"")
        this.buildConfigField("String", "UNSPLASH_ACCESS_KEY", "\"5YV5uUUDCkicQ4GmkPxSHdj6Xxt9IfX1fNIRR2ZPNZc\"")
        this.buildConfigField("String", "UNSPLASH_SECRET_KEY", "\"7B-zW_hEttrsGBNZxT7MZ9Jj_cDirDMzgU9fSx5MM6w\"")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activityKtx)

    //Lifecycle
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gsonConvertor)
    implementation(libs.okHttp)
    implementation(libs.okHttp.logging.intercepter)
    implementation(libs.jsonConvertor)

    // room
    implementation(libs.roomRuntime)
    implementation(libs.roomRuntimeKtx)
    implementation(libs.roomPaging)
    ksp(libs.roomCompiler)

    // coroutine
    implementation(libs.coroutineAndroid)
    implementation(libs.coroutineKotlin)

    //Kotlin Serialization
    implementation(libs.kotlinSerialization)

    //Dagger Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.hilt.compiler)

    //Paging Library
    implementation(libs.paging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //implementation("androidx.multidex:multidex:2.0.1")
}