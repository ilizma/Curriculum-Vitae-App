package com.ilizma.personal.flow.navigator

import android.app.Activity

class PersonalDataScreenBackCloseNavigatorImp(
    private val activity: Activity
) : PersonalDataScreenBackCloseNavigator {

    override fun close() {
        activity.finish()
    }

}