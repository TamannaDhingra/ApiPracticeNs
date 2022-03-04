package com.android.getapi.retroapi

import android.provider.ContactsContract
import com.android.getapi.models.GetAllDetailsModel
import com.android.getapi.models.GetUserLoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService{

    @GET("marvel/")
    fun getUserDetails(): Call<List<GetAllDetailsModel>>

    @POST("login")
    fun getLoginDetails(@Body rqstbody:RequestBody):Call<GetUserLoginResponse>
}

