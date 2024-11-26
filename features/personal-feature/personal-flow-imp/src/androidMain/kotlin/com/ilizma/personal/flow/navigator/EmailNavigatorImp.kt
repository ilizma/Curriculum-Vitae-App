package com.ilizma.personal.flow.navigator

import android.content.Context
import android.content.Intent

class EmailNavigatorImp(
    private val context: Context,
) : EmailNavigator {

    override fun navigate(
        email: String,
    ) {
        Intent(Intent.ACTION_SEND)
            .apply {
                type = "vnd.android.cursor.item/email"
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            }
            .let { context.startActivity(it) }
    }

}