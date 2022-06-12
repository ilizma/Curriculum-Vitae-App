plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.ilizma.curriculumvitaeapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug")
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)
    implementation(Android.v4)
    implementation(CustomActivityOnCrash.customactivityoncrash)
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)

    // api
    implementation(project(":api-di"))
    implementation(project(":api"))

    // net
    implementation(project(":net-di"))
    implementation(project(":net"))

    // di
    implementation(project(":di-base"))

    // View
    implementation(project(":view-base"))

    // Presentation
    implementation(project(":presentation-base"))

    // Resources
    implementation(project(":resources"))

    // region App
    implementation(project(":app-di"))
    implementation(project(":app-flow"))
    implementation(project(":app-flow-imp"))
    implementation(project(":app-view"))
    // endregion

    // region Error Management
    implementation(project(":error-management-di"))
    implementation(project(":error-management-view"))
    implementation(project(":error-management-view-imp"))
    // endregion

}
