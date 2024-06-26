package com.example.chatapp.feature.newChat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.streamliners.base.taskState.comp.whenLoaded
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold

@Composable
fun NewChatScreen(viewModel: NewChatViewModel,
                  navController: NavController) {

    LaunchedEffect(key1 = Unit ) {
        viewModel.start()
    }
    TitleBarScaffold(title = "New Chat", navigateUp = {
        navController.navigateUp()
    })
    {

        viewModel.usersListTask.whenLoaded { usersList ->
            LazyColumn(modifier = Modifier.padding(it),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
              //  items(usersList)

            }
        }

    }
}