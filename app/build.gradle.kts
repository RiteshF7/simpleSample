import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.room)
}


android {


    namespace = "com.trex.simplesample"
    compileSdk = 36


    defaultConfig {
        applicationId = "com.trex.simplesample"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }




    buildFeatures {
        compose = true
    }


    room {
        schemaDirectory("$projectDir/schemas")
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


    //Local unit test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)

    //UI test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.navigation.testing.v260)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Jetpack Compose integration
    implementation(libs.androidx.navigation.compose)
    implementation (libs.androidx.hilt.navigation.compose)
    implementation (libs.androidx.lifecycle.runtime.compose)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

//    Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //coil
    implementation (libs.coil.compose)



    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)


    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)


//    //Retrofit
//    implementation(libs.retrofit)
//    implementation(libs.converter.gson)
//    implementation(libs.logging.interceptor)

//     implementation(libs.androidx.browser)

//    //ViewModel
//    implementation(libs.androidx.lifecycle.viewmodel.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//
//    //Recycler View
//    implementation(libs.androidx.recyclerview)
//
//    //Glide
//    implementation(libs.glide)


    implementation(libs.androidx.browser)
//
//    //Room
//    implementation(libs.androidx.room.runtime)
//    implementation(libs.androidx.room.ktx)
//    ksp(libs.androidx.room.compiler)
//
//    //Paging
//    implementation(libs.androidx.paging.compose)
//
//    //WorkManager
//    implementation(libs.androidx.work.runtime.ktx)
//    implementation(libs.androidx.hilt.work)
//    ksp(libs.androidx.hilt.compiler)

}