package com.example.chatapp.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {



    // Surface with background color and elevation
    Surface(
        color = Color.Blue,
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                Text("Demo App Yt")
            },
            navigationIcon = {
                IconButton(onClick = { /* Handle navigation icon click */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "menu")
                }
            },
            actions = {
                IconButton(onClick = { /* Handle notifications icon click */ }) {
                    Icon(Icons.Filled.Notifications, contentDescription = "noti")
                }
                IconButton(onClick = { /* Handle search icon click */ }) {
                    Icon(Icons.Filled.Search, contentDescription = "search")
                }
            },
        )
    }
    Box(modifier = Modifier
        .fillMaxSize()
        ,
        contentAlignment = Alignment.Center) {
        Text(text = "No chat found!")
    }

}