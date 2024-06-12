plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "com.example.musicapp"
    compileSdkVersion(34)

    defaultConfig {
        applicationId = "com.example.musicapp"
        minSdkVersion(24)
        targetSdkVersion(34)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:${libs.androidx.core.ktx}")
    implementation("androidx.appcompat:appcompat:1.4.0") // Thay đổi phiên bản tại đây
    implementation("com.google.android.material:material:1.5.0") // Thay đổi phiên bản tại đây
    implementation("androidx.activity:activity-ktx:${libs.androidx.activity}")
    implementation("androidx.constraintlayout:constraintlayout:${libs.androidx.constraintlayout}")
    testImplementation("junit:junit:${libs.junit}")
    androidTestImplementation("androidx.test.ext:junit:${libs.androidx.junit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${libs.androidx.espresso.core}")

    implementation("de.hdodenhof:circleimageview:3.1.0") // Thêm thư viện CircleImageView phiên bản 3.1.0
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3") // Thêm thư viện Navigation Fragment KTX phiên bản 2.5.3
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3") // Thêm thư viện Navigation UI KTX phiên bản 2.5.3
}

