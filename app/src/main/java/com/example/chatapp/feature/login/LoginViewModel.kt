package com.example.chatapp.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.chatapp.data.LocalRepo
import com.example.chatapp.data.remote.UserRepo
import com.example.chatapp.navigation.Routes
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepo: UserRepo ,
    private  val localRepo: LocalRepo
    ): ViewModel() {

        fun onLoggedIn(email: String, navController: NavHostController) {

            viewModelScope.launch {
                val user = userRepo.getUserWithEmail(email)

                if (user != null){
                    //saved in local and navigate to home screen

                    localRepo.onLoggedIn()
                    navController.navigate(Routes.Home.routes)

            }
                else{

                    //navigate to edit profile screen
                    navController.navigate(Routes.EditProfileScreen.routes)

                }
            }

        }

}