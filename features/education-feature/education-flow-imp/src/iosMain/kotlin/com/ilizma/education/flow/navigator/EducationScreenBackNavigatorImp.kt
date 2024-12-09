package com.ilizma.education.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.personal.flow.model.PersonalTab

class EducationScreenBackNavigatorImp : EducationScreenBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        navController.popBackStack(
            route = PersonalTab,
            inclusive = false,
        )
    }

}