package com.example.chatapp.navigation

sealed class Routes(val routes: String) {
    object LoginScreen : Routes("login")
    object SplashScreen : Routes("splash")

    object Home : Routes("Home")
    object EditProfileScreen : Routes("editprofile")


}