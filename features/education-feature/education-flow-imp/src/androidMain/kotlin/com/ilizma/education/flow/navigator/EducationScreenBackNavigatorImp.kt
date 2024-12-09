package com.ilizma.education.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.ilizma.personal.flow.model.PersonalTab

class EducationScreenBackNavigatorImp : EducationScreenBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            navController.popBackStack(
                route = PersonalTab,
                inclusive = false,
            )
        }
    }

}