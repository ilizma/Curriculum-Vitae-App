plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines)
            implementation(libs.lifecycle.viewmodel)

            //region App
            implementation(project(":main-view"))
            // endregion

            //region Personal
            implementation(project(":personal-flow"))
            implementation(project(":personal-view"))
            implementation(project(":personal-presentation"))
            // endregion
        }
    }
}

android {
    namespace = "com.ilizma.personal.flow.imp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    flavorDimensions.add("variant")

    productFlavors {
        create("lrhr") {
            dimension = "variant"
            isDefault = true
        }

        create("ilizma") {
            dimension = "variant"
        }
    }

}

dependencies {
    // region Test
    testImplementation(project(":test-base"))
    // endregion
}
