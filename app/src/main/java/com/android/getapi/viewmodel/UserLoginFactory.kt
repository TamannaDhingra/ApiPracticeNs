package com.android.getapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.getapi.repo.Repositories

class UserLoginFactory(repo:Repositories):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserLogin() as T
    }
}