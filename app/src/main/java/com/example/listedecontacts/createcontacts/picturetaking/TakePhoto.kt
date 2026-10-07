package com.example.listedecontacts.createcontacts.picturetaking

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TakePhoto(
    takePicture: ManagedActivityResultLauncher<Uri, Boolean>,
    context: Context,
    changePendingURI: (Uri?) -> Unit
) {
    Button(
        modifier = Modifier.width(125.dp), onClick = {
            val uri = createURI(context) ?: return@Button
            changePendingURI(uri)
            try {
                takePicture.launch(uri)
            } catch (_ :ActivityNotFoundException) {
                context.contentResolver.delete(uri, null, null)
                changePendingURI(null)
            }
        }) {
        Text(text = "Take Photo")
    }
}

fun createURI(context: Context): Uri? {
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(
            MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg"
        )
    }

    return context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
    )
}