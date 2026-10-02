package com.example.listedecontacts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.listedecontacts.data.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(isEditing: Boolean, addContact: () -> Unit, removeContact: () -> Unit) {
    TopAppBar(title = { Text(text = "Contact App") }, actions = {
        if (isEditing) RemoveContact(removeContact)
        else AddContact(addContact)
    })
}

@Composable
fun AddContact(addContact: () -> Unit) {
    IconButton(onClick = { addContact() }) {
        Icon(
            imageVector = Icons.Default.Add, contentDescription = ""
        )
    }
}

@Composable
fun RemoveContact(removeContact: () -> Unit) {
    IconButton(onClick = { removeContact() }) {
        Icon(
            imageVector = Icons.Default.Delete, contentDescription = ""
        )
    }
}