package com.example.chatapp.data

import com.example.chatapp.helper.DataStoreUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LocalRepo(
    val dataStoreUtil: DataStoreUtil
) {

    suspend fun onLoggedIn(){

        dataStoreUtil.setData("IsLoggedIn" , true)
    }

    suspend fun isLoggedIn(): Boolean{
        return dataStoreUtil.getData<Boolean>("IsLoggedIn") ?: false
    }

}

