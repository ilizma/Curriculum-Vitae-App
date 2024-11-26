package com.ilizma.main.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.education.view.router.EducationScreenRouter
import com.ilizma.main.view.model.BottomNavigation
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.personal.view.router.PersonalDataScreenRouter
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import com.ilizma.work.view.router.WorkScreenRouter
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(
    personalDataScreenRouter: PersonalDataScreenRouter,
) {
    CurriculumVitaeAppTheme(dynamicColor = false) {
        InitScreens(
            personalDataScreenRouter = personalDataScreenRouter,
        )
    }
}

@Composable
private fun InitScreens(
    personalDataScreenRouter: PersonalDataScreenRouter,
) {
    val personalDataScreenViewModel: PersonalDataScreenViewModel = koinViewModel()
    val educationScreenViewModel: EducationScreenViewModel = koinViewModel()
    val workScreenViewModel: WorkScreenViewModel = koinViewModel()

    val educationScreenRouter: EducationScreenRouter = koinInject()
    val workScreenRouter: WorkScreenRouter = koinInject()

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val bottomNavController = rememberNavController()

    personalDataScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = personalDataScreenViewModel
    )

    educationScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = educationScreenViewModel
    )

    workScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = workScreenViewModel
    )

    Content(
        navController = navController,
        bottomNavController = bottomNavController,
        personalDataScreenViewModel = personalDataScreenViewModel,
        educationScreenViewModel = educationScreenViewModel,
        workScreenViewModel = workScreenViewModel,
    )
}

@Composable
private fun Content(
    navController: NavHostController,
    bottomNavController: NavHostController,
    personalDataScreenViewModel: PersonalDataScreenViewModel,
    educationScreenViewModel: EducationScreenViewModel,
    workScreenViewModel: WorkScreenViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigation
    ) {
        composable<BottomNavigation> {
            BottomNavigation(
                navController = bottomNavController,
                personalDataScreenViewModel = personalDataScreenViewModel,
                educationScreenViewModel = educationScreenViewModel,
                workScreenViewModel = workScreenViewModel,
            )
        }

        /*composable<ScheduleDetail> { backStackEntry ->
            ScheduleDetailScreen(
                viewModel = scheduleDetailScreenViewModel
                    .also { vm ->
                        backStackEntry.toRoute<ScheduleDetail>()
                            .let {
                                vm.saveCache(
                                    id = it.id,
                                    name = it.name
                                )
                            }
                    }
                    .also { it.getSchedule() },
            )
        }*/
    }
}