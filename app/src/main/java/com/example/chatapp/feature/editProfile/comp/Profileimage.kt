package com.example.chatapp.feature.editProfile.comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.streamliners.pickers.media.PickedMedia


@Composable
fun ProfileImage(pickedMedia: PickedMedia,
                   modifier: Modifier,
                   onClick: () -> Unit){

    val context = LocalContext.current

    val imageRequest = ImageRequest.Builder(context)
        .data(pickedMedia.uri)
        .crossfade(true)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = "Profile Image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    )
}