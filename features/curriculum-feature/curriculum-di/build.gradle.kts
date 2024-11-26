import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.buildkonfig)
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
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.firebase.firestore)

            // region Api
            implementation(project(":api"))
            // endregion

            // region Resources
            implementation(project(":resources"))
            // endregion

            // region Curriculum
            api(project(":curriculum-domain"))
            api(project(":curriculum-data"))
            api(project(":curriculum-data-imp"))
            // endregion
        }
    }
}

android {
    namespace = "com.ilizma.curriculum.di"
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

project.extra.set("buildkonfig.flavor", currentBuildVariant())

private fun Project.currentBuildVariant(): String {
    val variants = setOf("lrhr", "ilizma")
    return getAndroidBuildVariantOrNull()
        ?: System.getenv()["VARIANT"]
            .toString()
            .takeIf { it in variants } ?: "lrhr"
}

private fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("lrhr", "ilizma")
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(taskRequestsStr)
    val variant = if (matcher.find()) matcher.group(1).lowercase() else null
    return if (variant in variants) {
        variant
    } else {
        null
    }
}

buildkonfig {
    packageName = "com.ilizma.curriculum.di"

    defaultConfigs {
        buildConfigField(STRING, "CV_ID", CvId.lrhr)
    }

    defaultConfigs("lrhr") {
        buildConfigField(STRING, "CV_ID", CvId.lrhr)
    }
    defaultConfigs("ilizma") {
        buildConfigField(STRING, "CV_ID", CvId.ilizma)
    }
}
