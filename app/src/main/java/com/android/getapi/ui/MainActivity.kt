package com.android.getapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.getapi.R
import com.android.getapi.builderobjects.RetroBuilder
import com.android.getapi.builderobjects.RetroBuilder.service
import com.android.getapi.databinding.ActivityMainBinding
import com.android.getapi.models.GetAllDetailsModel
import com.android.getapi.repo.Repositories
import com.android.getapi.retroapi.ApiService
import com.android.getapi.retroapi.NetworkAdapter
import com.android.getapi.viewmodel.GetAllDetails
import com.android.getapi.viewmodel.GetAllDetailsFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var list:List<GetAllDetailsModel>

    lateinit var  layoutManager: LinearLayoutManager
    lateinit var recyclerAdapter:AdapterClass
    lateinit var retro: ApiService
    lateinit var repo:Repositories
    lateinit var viewmodel: GetAllDetails


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        list= arrayListOf()


        retro = service
        repo = Repositories(retro, this)
       viewmodel=ViewModelProvider(this,GetAllDetailsFactory(repo))
           .get(GetAllDetails::class.java)


        viewmodel.getDetails()

        viewmodel.data3.observe(this, Observer {
            if(it!=null){

                recyclerAdapter = AdapterClass( it)
                layoutManager = LinearLayoutManager(this)
                binding.rv.layoutManager=layoutManager
                binding.rv.adapter = recyclerAdapter

                binding.rv.addItemDecoration(
                    DividerItemDecoration(
                        baseContext,
                        layoutManager.orientation
                    )
                )


            }
        })

    }
}