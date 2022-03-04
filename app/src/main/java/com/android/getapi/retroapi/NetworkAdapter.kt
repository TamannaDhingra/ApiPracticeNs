package com.android.getapi.retroapi

import com.android.getapi.builderobjects.RetroBuilder

object NetworkAdapter {

    fun getRetrofitInstanceAll(): ApiService {
     return RetroBuilder.GetDetail().create(ApiService::class.java)
    }

   fun getLoginDetailsInstance():ApiService{
        return RetroBuilder.UserLogin().create(ApiService::class.java)
    }

}