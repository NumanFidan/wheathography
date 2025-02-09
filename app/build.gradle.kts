import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
}

android {
    namespace = "com.simplertutorials.android.wheathograophy"
    compileSdk = 35


    defaultConfig {
        applicationId = "com.simplertutorials.android.wheathograophy"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //load the API key from  .properties file
        val keystoreFile = project.rootProject.file("apiKey.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        //return empty key in case something goes wrong
        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = properties.getProperty("api.key") ?: ""
        )
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
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.appcompat)
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation ("androidx.databinding:viewbinding:8.8.0")
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    implementation "androidx.core:core-ktx:1.7.0"
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
//    implementation 'androidx.recyclerview:recyclerview:1.2.1'
//


    //retrofit and Gson
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.3.0")

    //OkHttp and OkHttpLoggingInterceptor
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Rx
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.0")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.0")

    implementation ("com.google.android.material:material:1.12.0")

    //Swipe To Refresh
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")
}
kapt {
    correctErrorTypes = true
}