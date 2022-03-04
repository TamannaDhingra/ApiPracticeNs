package com.android.getapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.getapi.R
import com.android.getapi.builderobjects.RetroBuilder
import com.android.getapi.databinding.ActivityLoginBinding
import com.android.getapi.repo.Repositories
import com.android.getapi.retroapi.ApiService
import com.android.getapi.retroapi.NetworkAdapter
import com.android.getapi.viewmodel.GetAllDetails
import com.android.getapi.viewmodel.GetAllDetailsFactory
import com.android.getapi.viewmodel.UserLogin
import com.android.getapi.viewmodel.UserLoginFactory
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity(), TextWatcher {

    lateinit var binding: ActivityLoginBinding
    lateinit var retro:ApiService
    lateinit var repo:Repositories
    lateinit var viewmodel:UserLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.etEmail.addTextChangedListener(this)


        retro =RetroBuilder.serviceLogin
        repo = Repositories(retro, this)
        viewmodel= ViewModelProvider(this, UserLoginFactory(repo))
            .get(UserLogin::class.java)
        
        binding.submitBtn.setOnClickListener { 
            val getEmail = binding.etEmail.text
            val getPass =binding.etPass.text
            
            viewmodel.getLoginDetails(getEmail.toString(),getPass.toString())


        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {

        if (binding.etEmail.getText().toString().startsWith(" "))
            binding.etEmail.setText("")
    }


}