package com.example.chatapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FireStoreCollections {


    fun FirebaseFirestore.usersColl() = collection("users")
}