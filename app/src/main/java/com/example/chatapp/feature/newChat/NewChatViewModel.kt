package com.example.chatapp.feature.newChat

import com.example.chatapp.data.remote.UserRepo
import com.example.chatapp.domain.model.User
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf

class NewChatViewModel(private  val userRepo: UserRepo):BaseViewModel(

) {

    val usersListTask = taskStateOf<List<User>>()

fun start(){
    execute {
        usersListTask.load { userRepo.getAllUsers() }
    }

}
}