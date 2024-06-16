package com.example.chatapp.feature.editProfile

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.LocalRepo
import com.example.chatapp.data.remote.StorageRepo
import com.example.chatapp.data.remote.UserRepo
import com.example.chatapp.domain.model.User
import com.streamliners.pickers.media.PickedMedia
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private  val localRepo: LocalRepo,
    private val storageRepo: StorageRepo
): ViewModel() {

    fun saveUser(user: User, image: PickedMedia?, onSuccess: ()-> Unit){

        viewModelScope.launch {




    //    val profileImageUrl = uploadProfileImage(image)
            val updatedUser = user.copy(profileImageUrl = uploadProfileImage(user.email , image))

            UserRepo().saveUser(user = updatedUser)
            localRepo.onLoggedIn()
            onSuccess()
        }


}

    private suspend fun uploadProfileImage(email: String, image: PickedMedia?): String? {
        return image?.let {
            storageRepo.uploadFile("profileimages/$email.jpg", image.uri.toUri())
        }
    }
    }

