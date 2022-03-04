package com.android.getapi.models

data class GetUserLoginResponse(
    var password:String,
    var job_title:String,
    var email:String,
    var phone:String,
    var name:String,
    var profile_picture:String
)

