package com.example.chatapp.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.chatapp.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController,
                viewModel: LoginViewModel) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Welcome to Chat App", color = Color.White) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )    }) { paddingValues ->

      val context = LocalContext.current

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            contentAlignment = Alignment.Center) {

            SignInWithGoogleButton(onSuccess = { user ->
                 val email = user.email ?: error("email not found!")

                viewModel.onLoggedIn(email =email , navController =navController)

               /* Toast.makeText(context, "Sign in as $email}" ,
                    Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.EditProfileScreen.routes)*/
            }, onError = {
                Toast.makeText(context, "Error ${it?.message}" ,
                    Toast.LENGTH_SHORT).show()
            })

        }

    }

}