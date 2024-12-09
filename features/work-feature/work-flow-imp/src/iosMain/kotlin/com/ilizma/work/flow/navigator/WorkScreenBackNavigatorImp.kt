package com.ilizma.work.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.personal.flow.model.PersonalTab

class WorkScreenBackNavigatorImp : WorkScreenBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        navController.popBackStack(
            route = PersonalTab,
            inclusive = false,
        )
    }

}