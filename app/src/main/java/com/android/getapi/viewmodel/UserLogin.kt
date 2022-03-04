package com.android.getapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.getapi.builderobjects.RetroBuilder
import com.android.getapi.builderobjects.RetroBuilder.serviceLogin
import com.android.getapi.models.GetAllDetailsModel
import com.android.getapi.models.GetUserLoginResponse
import com.android.getapi.retroapi.NetworkAdapter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class UserLogin:ViewModel() {

    val data3= MutableLiveData<GetUserLoginResponse>()
    val datatemp: LiveData<GetUserLoginResponse>
        get()=data3

    fun getLoginDetails(email:String,password:String){

        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("password", password)

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())


        serviceLogin.getLoginDetails(requestBody).enqueue(object : retrofit2.Callback<GetUserLoginResponse> {


            override fun onResponse(
                call: Call<GetUserLoginResponse>,
                response: Response<GetUserLoginResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("TAG", "onResponse: Success Login")
                    data3.postValue(response.body())

                    Log.d("TAG", "onResponse: UserLoginDetails" +response.body())

                }
                else{
                    Log.d("TAG", "onResponse: Success Login"+response.message())
                }
            }

            override fun onFailure(call: Call<GetUserLoginResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: Failure Login")
            }
        })


    }
    }

