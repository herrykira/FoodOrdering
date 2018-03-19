package com.example.kinhangpoon.foodordering.utility

import com.google.android.gms.identity.intents.model.UserAddress

/**
 * Created by hefen on 2/24/2018.
 */

object AccountDescription {
    var msg = ""
    var UserID = ""
    var UserName = ""
    var UserEmail = ""
    var UserAddress = ""
    var UserMobile = ""
    var AppApiKey = ""
    var login = ""
    fun clearRecord() {
        login = ""
    }
}
