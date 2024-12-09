package com.ilizma.personal.flow.navigator

import android.app.Activity

class PersonalDataScreenCloseNavigatorImp(
    private val activity: Activity
) : PersonalDataScreenCloseNavigator {

    override fun close() {
        activity.finish()
    }

}