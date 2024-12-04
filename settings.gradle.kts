@file:Suppress("UnstableApiUsage")

rootProject.name = "CurriculumVitaeApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(
    // region App
    ":composeApp",
    // endregion

    // region base
    ":view-base",
    // endregion

    // resources
    ":resources",
    // endregion

    // region Main
    ":main-di",
    ":main-view",
    // endregion

    // region Error Management
    ":error-management-di",
    ":error-management-view",
    ":error-management-view-imp",
    // endregion

    // region Curriculum
    ":curriculum-di",
    ":curriculum-domain",
    ":curriculum-data",
    ":curriculum-data-imp",
    // endregion

    // region Personal
    ":personal-di",
    ":personal-flow",
    ":personal-flow-imp",
    ":personal-view",
    ":personal-presentation",
    ":personal-presentation-imp",
    ":personal-domain",
    ":personal-domain-imp",
    // endregion

    // region Education
    ":education-di",
    ":education-flow",
    ":education-flow-imp",
    ":education-view",
    ":education-presentation",
    ":education-presentation-imp",
    ":education-domain",
    ":education-domain-imp",
    // endregion

    // region Work
    ":work-di",
    ":work-flow",
    ":work-flow-imp",
    ":work-view",
    ":work-presentation",
    ":work-presentation-imp",
    ":work-domain",
    ":work-domain-imp",
    // endregion

)

// region CORE
// region Resources
project(":resources").projectDir = File("core/resources")
// endregion Resources

// region Base
project(":view-base").projectDir = File("core/view-base")
// endregion Base
// endregion CORE

// region FEATURES
// region Main
project(":main-di").projectDir = File("features/main-feature/main-di")
project(":main-view").projectDir = File("features/main-feature/main-view")
// endregion Main

// region Error Management
project(":error-management-di").projectDir = File("features/error-management-feature/error-management-di")
project(":error-management-view").projectDir = File("features/error-management-feature/error-management-view")
project(":error-management-view-imp").projectDir = File("features/error-management-feature/error-management-view-imp")
// endregion Error Management

// region Curriculum
project(":curriculum-di").projectDir = File("features/curriculum-feature/curriculum-di")
project(":curriculum-domain").projectDir = File("features/curriculum-feature/curriculum-domain")
project(":curriculum-data").projectDir = File("features/curriculum-feature/curriculum-data")
project(":curriculum-data-imp").projectDir = File("features/curriculum-feature/curriculum-data-imp")
// endregion Curriculum

// region Personal
project(":personal-di").projectDir = File("features/personal-feature/personal-di")
project(":personal-flow").projectDir = File("features/personal-feature/personal-flow")
project(":personal-flow-imp").projectDir = File("features/personal-feature/personal-flow-imp")
project(":personal-presentation").projectDir = File("features/personal-feature/personal-presentation")
project(":personal-presentation-imp").projectDir = File("features/personal-feature/personal-presentation-imp")
project(":personal-domain").projectDir = File("features/personal-feature/personal-domain")
project(":personal-domain-imp").projectDir = File("features/personal-feature/personal-domain-imp")
project(":personal-view").projectDir = File("features/personal-feature/personal-view")
// endregion Personal

// region Education
project(":education-di").projectDir = File("features/education-feature/education-di")
project(":education-flow").projectDir = File("features/education-feature/education-flow")
project(":education-flow-imp").projectDir = File("features/education-feature/education-flow-imp")
project(":education-presentation").projectDir = File("features/education-feature/education-presentation")
project(":education-presentation-imp").projectDir = File("features/education-feature/education-presentation-imp")
project(":education-domain").projectDir = File("features/education-feature/education-domain")
project(":education-domain-imp").projectDir = File("features/education-feature/education-domain-imp")
project(":education-view").projectDir = File("features/education-feature/education-view")
// endregion Education

// region Work
project(":work-di").projectDir = File("features/work-feature/work-di")
project(":work-flow").projectDir = File("features/work-feature/work-flow")
project(":work-flow-imp").projectDir = File("features/work-feature/work-flow-imp")
project(":work-presentation").projectDir = File("features/work-feature/work-presentation")
project(":work-presentation-imp").projectDir = File("features/work-feature/work-presentation-imp")
project(":work-domain").projectDir = File("features/work-feature/work-domain")
project(":work-domain-imp").projectDir = File("features/work-feature/work-domain-imp")
project(":work-view").projectDir = File("features/work-feature/work-view")
// endregion Work
// endregion FEATURES