package com.ilizma.personal.flow.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri

class PhoneNavigatorImp(
    private val context: Context,
) : PhoneNavigator {

    override fun navigate(
        phoneNumber: String,
    ) {
        phoneNumber
            .let { "tel:$it" }
            .let { Uri.parse(it) }
            .let { Intent(Intent.ACTION_DIAL, it) }
            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            .let { context.startActivity(it) }
    }

}