package com.example.chatapp.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.chatapp.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navHostController: NavHostController , viewModel: SplashViewModel) {

    LaunchedEffect(key1 = Unit) {

        viewModel.checkLoginStatus(navHostController)

    }

    /*LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("login")
    }*/

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF150099), Color(0xFF6E008A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.ic_message),
            contentDescription = "chat",
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}