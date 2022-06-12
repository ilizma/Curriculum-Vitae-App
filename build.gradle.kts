import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(Project.buildGradle)
        classpath(Project.kotlinGradle)
        classpath(Project.navigation)
        classpath(Project.hilt)
        classpath(Project.junit)
    }
}

allprojects {
    repositories {
        maven {
            setUrl("https://maven.google.com/")
            name = "Google"
        }
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

fun setDefaultKotlinJvmTarget(tasks: TaskContainer) {
    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}