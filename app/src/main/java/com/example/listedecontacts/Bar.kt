package com.example.listedecontacts

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.listedecontacts.data.ContactViewModel
import com.example.listedecontacts.ui.theme.DarkColorScheme
import com.example.listedecontacts.ui.theme.LightColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(isEditing: Boolean, addContact: () -> Unit, removeContact: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Contact App") },
        actions = {
            if (isEditing) RemoveContact(removeContact)
            else AddContact(addContact)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        )

    )
}

@Composable
fun AddContact(addContact: () -> Unit) {
    IconButton(onClick = { addContact() }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun RemoveContact(removeContact: () -> Unit) {
    IconButton(onClick = { removeContact() }) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground
            )
    }
}