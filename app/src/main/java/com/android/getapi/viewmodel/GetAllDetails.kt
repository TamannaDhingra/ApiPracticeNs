package com.android.getapi.viewmodel

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.getapi.builderobjects.RetroBuilder.service
import com.android.getapi.models.GetAllDetailsModel
import com.android.getapi.retroapi.NetworkAdapter
import retrofit2.Call
import retrofit2.Response

class GetAllDetails:ViewModel() {

    val data3=MutableLiveData<List<GetAllDetailsModel>>()
    val datatemp:LiveData<List<GetAllDetailsModel>>
    get()=data3


    fun getDetails(){


         service.getUserDetails().enqueue(object : retrofit2.Callback<List<GetAllDetailsModel>> {
            override fun onResponse(
                call: Call<List<GetAllDetailsModel>>,
                response: Response<List<GetAllDetailsModel>>
            ) {
                if(response.isSuccessful){
                    // progress.visibility= View.VISIBLE
                    Log.d("TAG", "onResponse: Success DhabaDetail")
                    data3.postValue(response.body())
                    // progress.visibility= View.GONE
                }
                else{
                     Log.d("TAG", "onResponse: Success AllDetail"+response.message())
                }

            }

            override fun onFailure(
                call: Call<List<GetAllDetailsModel>>,
                t: Throwable
            ) {
                Log.d("TAG", "onFailure: Failure DhabaDetail")

            }
        })


    }
}


