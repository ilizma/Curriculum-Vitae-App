package com.ilizma.personal.flow.navigator

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class PhoneNavigatorImp : PhoneNavigator {

    override fun navigate(
        phoneNumber: String,
    ) {
        phoneNumber
            .let { "tel://$it" }
            .let { NSURL(string = it) }
            .let { url ->
                if (UIApplication.sharedApplication.canOpenURL(url)) {
                    UIApplication.sharedApplication.openURL(
                        url = url,
                        options = mapOf<Any?, Any?>(),
                        completionHandler = {
                            if (!it) {
                                println("Failed to open URL: $url")
                            }
                        },
                    )
                }
            }
    }

}