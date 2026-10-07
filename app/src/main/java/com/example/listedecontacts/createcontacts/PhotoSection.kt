package com.example.listedecontacts.createcontacts

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.example.listedecontacts.TAG
import com.example.listedecontacts.createcontacts.picturetaking.TakePhoto

@Composable
fun PhotoSection(name: String, photo: String, getPhoto: (uri: String) -> Unit) {
    val context = LocalContext.current

    var pendingURI by remember { mutableStateOf<Uri?>(null) }
    var photoURI by remember { mutableStateOf<Uri?>(null) }
    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        val uri = pendingURI
        if (success && uri != null) {
            photoURI = uri
            getPhoto(uri.toString())
        } else if (uri != null) {
            context.contentResolver.delete(uri, null, null)
        }
        pendingURI = null
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center
        ) {
            if (photo.isNotEmpty()) {
                photoURI = photo.toUri()
                photoURI?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize(.925f)
                            .clip(CircleShape)
                    )
                }
            } else if (name.isNotEmpty()) {
                Text(
                    name[0].toString().uppercase(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            TakePhoto(
                takePicture,
                context,
                { pendingURI = it },
            )
            Button(
                modifier = Modifier.width(125.dp),
                onClick = {}) {
                Text(text = "Gallery")
            }
        }
    }
}