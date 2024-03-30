plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        create("my_config") {
            storeFile = file("C:\\Users\\user\\Desktop\\test_key.jks")
            storePassword = "ngoquanghy123"
            keyAlias = "ngoquang3782@gmail.com"
            keyPassword = "ngoquanghy123"
        }
    }
    namespace = "com.example.quanlichitieu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quanlichitieu"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("my_config")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    repositories {

    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("net.sourceforge.jtds:jtds:1.3.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-auth")
    implementation("androidx.core:core-ktx:1.12.0")


}
