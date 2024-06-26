package com.example.chatapp.feature.editProfile

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.LocalRepo
import com.example.chatapp.data.remote.StorageRepo
import com.example.chatapp.data.remote.UserRepo
import com.example.chatapp.domain.model.User
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
import com.streamliners.pickers.media.PickedMedia
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private  val localRepo: LocalRepo,
    private val storageRepo: StorageRepo
): BaseViewModel() {
    val saveProfileTask = taskStateOf<Unit>()

    fun saveUser(user: User,
                 image: PickedMedia?,
                 onSuccess: ()-> Unit,

    )
    {
        execute(showLoadingDialog = false){

            saveProfileTask.load {  val updatedUser = user.copy(profileImageUrl = uploadProfileImage(user.email , image))

                UserRepo().saveUser(user = updatedUser)
                localRepo.onLoggedIn()

                withContext(Dispatchers.Main){
                    onSuccess()
                }

            }



        }



       /* val exceptionHandler = CoroutineExceptionHandler{_, error ->
            onError(error.message ?: "Unknown Error")
        }*/




}

    private suspend fun uploadProfileImage(email: String, image: PickedMedia?): String? {
        return image?.let {
            storageRepo.uploadFile("profileimages/$email.jpg", image.uri.toUri())
        }
    }
    }

