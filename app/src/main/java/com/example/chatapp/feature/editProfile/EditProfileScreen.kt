package com.example.chatapp.feature.editProfile

import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chatapp.domain.model.Gender
import com.example.chatapp.domain.model.User
import com.example.chatapp.feature.editProfile.comp.AddImageButton
import com.example.chatapp.feature.editProfile.comp.ProfileImage
import com.example.chatapp.navigation.Routes
import com.streamliners.compose.comp.select.RadioGroup
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.config.InputConfig
import com.streamliners.compose.comp.textInput.config.text
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.allHaveValidInputs
import com.streamliners.compose.comp.textInput.state.value
import com.streamliners.pickers.media.FromGalleryType
import com.streamliners.pickers.media.MediaPickerDialog
import com.streamliners.pickers.media.MediaPickerDialogState
import com.streamliners.pickers.media.MediaType
import com.streamliners.pickers.media.PickedMedia
import com.streamliners.pickers.media.rememberMediaPickerDialogState

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun EditProfileScreen(
    viewModel: EditProfileViewModel,
    email: String,
    navController: NavHostController
) {


    val mediaPickerDialogStore = rememberMediaPickerDialogState()

    val nameInput = remember {
        mutableStateOf(
            TextInputState(
                label = "Name",
                inputConfig = InputConfig.text {
                    minLength = 3
                    maxLength = 20
                }
            )
        )
    }


 /*val nameInput = remember {
        mutableStateOf(
            TextInputState(
                label = "Name",
                inputConfig = InputConfig.text {
                    minLength = 3
                    maxLength = 20
                }
            )
        )
    }*/


    val bioInput = remember {
        mutableStateOf(
            TextInputState(
                label = "Bio",
                inputConfig = InputConfig.text{
                    minLength = 5
                    maxLength = 50
                }
            )
        )
    }


    val scope = rememberCoroutineScope()


   var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

   var nameError by remember { mutableStateOf(false) }
    var bioError by remember { mutableStateOf(false) }


    val gender = remember {
        mutableStateOf<Gender?>(null)
    }

    var genderError by remember {
        mutableStateOf(false)
    }

   LaunchedEffect(key1 = gender.value){
      if(gender.value==null) genderError = false
   }

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        android.Manifest.permission.READ_MEDIA_IMAGES
    } else {
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                launcher.launch("image/*")
            } else {
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    val image = remember{
        mutableStateOf<PickedMedia?>(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Welcome to Chat App", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())


                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)


        ) {
            val initImagePicker = {
                mediaPickerDialogStore.value = MediaPickerDialogState.ShowMediaPicker(
                    type = MediaType.Image,
                    allowMultiple = false,
                    fromGalleryType = FromGalleryType.VisualMediaPicker
                ) { getList ->
                    scope.launch {
                        val list = getList()
                        list.firstOrNull()?.let {
                            image.value = it
                        }
                    }
                }
            }

            image.value?.let {
                ProfileImage(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    pickedMedia = it,
                    onClick = initImagePicker
                )
            } ?: run {
                AddImageButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = initImagePicker
                )
            }


            TextInputLayout(
                state = nameInput,
            )
 {

            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { },

                enabled = true,
                label = { Text(text = "Email") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextInputLayout(state = bioInput)
{

            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start // Align RadioGroup to the start
            )
            {
                RadioGroup(
                    title = "Select Gender",
                    state = gender,
                    options = Gender.entries.toList(),
                    labelExtractor = { it.name }
                )
                if (genderError){
                    Text(text = "select gender")
                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { if (TextInputState.allHaveValidInputs(nameInput , bioInput)
                      )

                {
                    gender.value?.let {
                        val user = User(
                        name = nameInput.value(),
                        email = email,
                            profileImageUrl = null,
                        bio = bioInput.value(),
                        gender = it
                    )

                        viewModel.saveUser(user , image.value){
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Registration Successfully")
                            }
                            navController.navigate(Routes.Home.routes)

                        }
                    }
                     }
                    if (gender.value == null){
                        genderError = true
                    }
                     },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text(
                    text = "Save",
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 20.sp),
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
    }

    MediaPickerDialog(state = mediaPickerDialogStore, authority = "com.example.chatapp.fileprovider" )
}

