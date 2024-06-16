package com.example.chatapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatapp.feature.editProfile.EditProfileScreen
import com.example.chatapp.feature.home.HomeScreen
import com.example.chatapp.feature.login.LoginScreen
import com.example.chatapp.feature.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.EditProfileScreen.routes) {
        composable(Routes.SplashScreen.routes) {
            SplashScreen(navController, koinViewModel())
        }
        composable(Routes.LoginScreen.routes) {
            LoginScreen(navController , koinViewModel())
        }

        composable("${Routes.EditProfileScreen.routes}?email={email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            EditProfileScreen(
                navController = navController,
                viewModel = koinViewModel(),
                email = email
            )
        }

        composable(Routes.Home.routes) {
            HomeScreen()
        }
    }
}