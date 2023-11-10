plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //JSONパース用
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "jp.ac.it_college.std.s22021.pokemonsilet"
    compileSdk = 34

    defaultConfig {
        applicationId = "jp.ac.it_college.std.s22021.pokemonsilet"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //HTTP通信用（OkHttp3）
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    //JSONパース用（serialization）
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    //URL画像表示用
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
}