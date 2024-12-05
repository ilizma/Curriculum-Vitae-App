
# Curriculum Vitae App by Iosu Lizarraga Madinabeitia

## Summary

Curriculum Vitae App is a Compose Multiplatform application that allows to see a CV, differentiated by flavor to be able to have multiple ones.

## App Flow

<img src="img/screens1.webp" alt="Screen 1 - Personal" width="25%"/>
<img src="img/screens2.webp" alt="Screen 2 - Academic" width="25%"/>
<img src="img/screens3.webp" alt="Screen 3 - Prof. experience" width="25%"/>

## App structure description

The app was made using Clean Architecture with MVVM-MVI pattern and reactive components with Coroutines and Flow. The app modularization was done thinking on improve the app time compilation and the fast bug detection. The modules are split in features and in CA layers as it can see in the following scheme:

## Modularization structure

![Clean Architecture](img/modularization.webp)

The responsibility to know the other layers, are in the -imp modules; the abstractions must not in any case be aware of other modules.

## Modules

#### Data module
Provide, send or keep the data info that the app needs to work.
I added a DataSource with the firestore connection to be easy to get the data, and I manage the caché inside the Repository.

#### Domain module
The business logic.
I added here the separation of different data used in different features: personal, academic and professional experience.

#### Presentation module
The viewModels. I created viewModels for each screen.

#### View module
The views, composable components, and the definitions of routers.

#### Flow module
The responsible to allow navigation between features and back navigation.

## Packaging
The packaging structure is based in four parts: app package path definition + feature + layer + component. For example:
com.ilizma.personal.presentation.viewmodel.

## Naming
The class naming is based in the feature division, the abstracted classes are called X.kt and the implementation classes are called XImp.kt.

## GitFlow

![GitFlow](img/gitflow.webp)![Squash and merge](img/squash.webp)

I usually use Squash and merge for features, so that they stay as a single commit in develop, and use Merge to merge develop into master, so we have a very clean git and it's much easier to follow the flow of it.
Github allows us to restore branches that have already been Squash and merged, so we don't lose the flow of commits for that feature.

In this case, I do a Merge to get visible all the steps that I made.

### Important used libraries
- Koin: To do the dependency injection.
- Coroutines/Flow: To get/save data asynchronously.
- Buildkonfig: To create variants and fields
- NavigationComponent: To navigate between views.
- Ktorfit, okhttp and darwin: To manage network.
- Serialization: To manage Json.
- Chucker: To see the network in a separated screen.
- CustomActivityOnCrash: To see Android crashes easily in a new screen.
- Compose ui test and runComposeUiTest: To do the instrumentation tests.
- Mockk: A Kotlin library to mock the objects on Tests.
- Kotlin test: To do the unit and ui tests.

### Annotation
This project contains Unit Test for all classes, and Composable screen instrumentation tests.

