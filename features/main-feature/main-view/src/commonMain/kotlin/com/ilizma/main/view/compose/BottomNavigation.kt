package com.ilizma.main.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ilizma.education.flow.model.EducationTab
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.education.view.compose.EducationScreen
import com.ilizma.main.view.model.BottomBarItemType
import com.ilizma.personal.flow.model.PersonalTab
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.personal.view.compose.PersonalDataScreen
import com.ilizma.resources.Res
import com.ilizma.resources.title_education
import com.ilizma.resources.title_personal
import com.ilizma.resources.title_work
import com.ilizma.work.flow.model.WorkTab
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import com.ilizma.work.view.compose.WorkScreen
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun BottomNavigation(
    navController: NavHostController,
    personalDataScreenViewModel: PersonalDataScreenViewModel,
    educationScreenViewModel: EducationScreenViewModel,
    workScreenViewModel: WorkScreenViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navigationBarItemType = rememberSaveable { mutableStateOf(BottomBarItemType.PERSONAL) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                key = navigationBarItemType.value,
            )
        },
        bottomBar = {
            BottomBar(
                key = navigationBarItemType.value,
                itemSelected = { navController.navigate(it) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Content(
            navigationBarItemType = { navigationBarItemType.value = it },
            navController = navController,
            snackbarHostState = snackbarHostState,
            paddingValues = paddingValues,
            personalDataScreenViewModel = personalDataScreenViewModel,
            educationScreenViewModel = educationScreenViewModel,
            workScreenViewModel = workScreenViewModel,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    key: BottomBarItemType,
) {
    TopAppBar(
        title = {
            Text(
                text = when (key) {
                    BottomBarItemType.PERSONAL -> stringResource(Res.string.title_personal)
                    BottomBarItemType.EDUCATION -> stringResource(Res.string.title_education)
                    BottomBarItemType.WORK -> stringResource(Res.string.title_work)
                }
            )
        },
    )
}

@Composable
private fun BottomBar(
    key: BottomBarItemType,
    itemSelected: (Any) -> Unit,
) {
    NavigationBar {
        BottomBarItem(
            selected = key == BottomBarItemType.PERSONAL,
            textResource = Res.string.title_personal,
            icon = if (key != BottomBarItemType.PERSONAL) Icons.Outlined.PersonOutline else Icons.Default.Person,
            contentDescription = "Personal",
            itemSelected = { itemSelected(PersonalTab) },
        )
        BottomBarItem(
            selected = key == BottomBarItemType.EDUCATION,
            textResource = Res.string.title_education,
            icon = if (key != BottomBarItemType.EDUCATION) Icons.Outlined.School else Icons.Default.School,
            contentDescription = "Education",
            itemSelected = { itemSelected(EducationTab) },
        )
        BottomBarItem(
            selected = key == BottomBarItemType.WORK,
            textResource = Res.string.title_work,
            icon = if (key != BottomBarItemType.WORK) Icons.Outlined.WorkOutline else Icons.Default.Work,
            contentDescription = "Work",
            itemSelected = { itemSelected(WorkTab) },
        )
    }
}

@Composable
private fun RowScope.BottomBarItem(
    selected: Boolean,
    textResource: StringResource,
    icon: ImageVector,
    contentDescription: String,
    itemSelected: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = { itemSelected() },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        },
        label = {
            Text(
                text = stringResource(textResource),
            )
        },
    )
}

@Composable
private fun Content(
    navigationBarItemType: (BottomBarItemType) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    personalDataScreenViewModel: PersonalDataScreenViewModel,
    educationScreenViewModel: EducationScreenViewModel,
    workScreenViewModel: WorkScreenViewModel,
) {
    NavHost(navController = navController, startDestination = PersonalTab) {
        composable<PersonalTab> {
            navigationBarItemType(BottomBarItemType.PERSONAL)
            PersonalDataScreen(
                viewModel = personalDataScreenViewModel,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
            )
        }

        composable<EducationTab> {
            navigationBarItemType(BottomBarItemType.EDUCATION)
            EducationScreen(
                viewModel = educationScreenViewModel,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
            )
        }

        composable<WorkTab> {
            navigationBarItemType(BottomBarItemType.WORK)
            WorkScreen(
                viewModel = workScreenViewModel,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
            )
        }
    }
}