package com.example.chatapp.data.remote



import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import android.net.Uri

class StorageRepo {

    suspend fun uploadFile(path: String, uri: Uri): String {
        val storageRef = FirebaseStorage.getInstance().reference.child(path)
        storageRef.putFile(uri).await()
        val downloadUrl = storageRef.downloadUrl.await()
            ?: throw IllegalStateException("Unable to get download URL")
        return downloadUrl.toString()
    }
}
