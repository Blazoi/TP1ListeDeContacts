package com.example.listedecontacts.createcontacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants

@Composable
fun CreateContact() {
    Column() {
        PhotoSection()
    }
}

@Composable
fun PhotoSection() {
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
                .background(Color.Blue)
        ) {
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Button(
                modifier = Modifier.width(125.dp),
                onClick = {}) {
                Text(text = "Take Photo")
            }
            Button(
                modifier = Modifier.width(125.dp),
                onClick = {}) {
                Text(text = "Gallery")
            }
        }
    }
}