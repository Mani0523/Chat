package com.example.chatapp.navigation



sealed class Routes(val routes: String) {
    object LoginScreen : Routes("login")
    object SplashScreen : Routes("splash")
    object Home : Routes("home")  // Ensure 'home' is lowercase
    object EditProfileScreen : Routes("editprofile")
    object NewChat : Routes("NewChat")
}
