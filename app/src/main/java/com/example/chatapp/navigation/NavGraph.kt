package com.example.chatapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.chatapp.MainActivity
import com.example.chatapp.feature.editProfile.EditProfileScreen
import com.example.chatapp.feature.home.HomeScreen
import com.example.chatapp.feature.login.LoginScreen
import com.example.chatapp.feature.splash.SplashScreen
import com.streamliners.base.ext.koinBaseViewModel
import com.streamliners.pickers.date.showDatePickerDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainActivity.NavGraph(navController: NavHostController) {


    /*LaunchedEffect(key1 = Unit  ) {

        delay(100)
        navController.navigate()

    }*/





    NavHost(navController = navController, startDestination = Routes.SplashScreen.routes) {
        composable(Routes.SplashScreen.routes) {
            SplashScreen(navController, koinViewModel())
        }
        composable(Routes.LoginScreen.routes) {
            LoginScreen(navController , koinViewModel())
        }

       /* composable(
            route = "${Routes.EditProfileScreen.routes}?email={email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val email = arguments.getString("email") ?: error("Email not provided")

            EditProfileScreen(
                navController = navController,
                viewModel = koinViewModel(),
                email = email,
                showDatePicker = ::showDatePickerDialog
            )
        }*/


        composable(
            route = "${Routes.EditProfileScreen.routes}?email={email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val email = arguments.getString("email") ?: error("Email not provided")
            EditProfileScreen(
                navController = navController,
                viewModel = koinBaseViewModel(),
                email = email,
                showDatePicker = ::showDatePickerDialog
            )
        }
        composable(Routes.Home.routes) {
            HomeScreen()
        }
    }
}