package com.example.chatapp.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.chatapp.data.LocalRepo
import com.example.chatapp.navigation.Routes
import kotlinx.coroutines.launch
import okhttp3.Route
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val localRepo: LocalRepo): ViewModel() {

    fun checkLoginStatus(navController: NavController){

        viewModelScope.launch {
         if (localRepo.isLoggedIn()){
             navController.navigate(Routes.Home.routes   )
         }else{
             navController.navigate(Routes.LoginScreen.routes   )
         }
        }

    }
}