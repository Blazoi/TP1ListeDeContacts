package com.example.listedecontacts.createcontacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfoSection(
    name: String,
    changeName: (String) -> Unit,
    phoneNumber: String,
    changePhoneNumber: (String) -> Unit,
    age: Int,
    changeAge: (Int) -> Unit,
    email: String,
    changeEmail: (String) -> Unit,
    address: String,
    changeAddress: (String) -> Unit,
    favorite: Boolean,
    changeFavorite: (Boolean) -> Unit,
    photo: String,
    changePhoto: (uri: String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PhotoSection(
            name,
            photo,
            { changePhoto(it) })

        OutlinedTextField(
            value = name,
            onValueChange = { changeName(it) },
            label = { Text(text = "Nom") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
            )
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { changePhoneNumber(it) },
            label = { Text(text = "Numéro de téléphone") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = age.toString(),
            onValueChange = { changeAge(if (it.isNotEmpty()) it.toInt() else 0) },
            label = { Text(text = "Age") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { changeEmail(it) },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address,
            onValueChange = { changeAddress(it) },
            label = { Text(text = "Adresse") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = favorite,
                onCheckedChange = { changeFavorite(it) }
            )
            Text("Favori")
        }
    }
}