package com.example.listedecontacts.contacts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import com.example.listedecontacts.data.Contact

@Composable
fun ContactList(contacts: List<Contact>) {
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(contacts) { contact ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(5f)
                    .background(Color.White)
                    .padding(5.dp)
                    .clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(.75f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {
                    if (contact.photo > 0) {
                        Image(
                            painter = painterResource(contact.photo),
                            contentDescription = "Photo",
                        )
                    } else {
                        Text(
                            text = contact.firstName[0].toString().capitalize(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            text = contact.firstName + " " + contact.lastName,
                            color = Color.Black
                        )
                        Text(
                            text = contact.phone,
                            color = Color.Black
                        )
                    }

                    if (contact.favorite)
                        Text(
                            text = "★",
                            color = Color.Black
                        )
                }
            }
            HorizontalDivider()
        }
    }
}