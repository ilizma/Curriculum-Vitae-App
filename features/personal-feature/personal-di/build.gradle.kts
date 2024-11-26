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
        androidMain.dependencies {
            implementation(libs.koin.android)

            implementation(project(":main-view"))
        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)

            // region Resources
            implementation(project(":resources"))
            // endregion

            // region Curriculum
            implementation(project(":curriculum-domain"))
            // endregion

            // region Personal
            api(project(":personal-flow"))
            api(project(":personal-flow-imp"))
            api(project(":personal-view"))
            api(project(":personal-presentation"))
            api(project(":personal-presentation-imp"))
            api(project(":personal-domain"))
            api(project(":personal-domain-imp"))
            // endregion
        }
    }
}

android {
    namespace = "com.ilizma.personal.di"
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
