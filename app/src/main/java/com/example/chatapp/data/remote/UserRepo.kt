package com.example.chatapp.data.remote

import com.example.chatapp.data.remote.FireStoreCollections.usersColl
import com.example.chatapp.domain.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserRepo {

    suspend fun saveUser(user : User) {
        Firebase.firestore.usersColl()
            .add(user)
            .await()

    }

    suspend fun getAllUsers(): List<User>{

     return   Firebase.firestore.usersColl()
            .get()
            .await()
            .toObjects(User::class.java)

    }

    suspend fun getUserWithEmail(email : String): User? {

      return  Firebase.firestore.usersColl()
            .whereEqualTo("email" , email)
            .limit(1)
            .get()
            .await()
            .toObjects(User::class.java)
            .firstOrNull()


    }



}