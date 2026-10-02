package com.example.listedecontacts.createcontacts

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listedecontacts.currentContact
import com.example.listedecontacts.data.Contact
import com.example.listedecontacts.data.ContactViewModel
import com.example.listedecontacts.isContactNew

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

@Composable
fun CreateContact(contactViewModel: ContactViewModel, backToContacts: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var name by remember { mutableStateOf(if (!isContactNew) currentContact.name else "") }
        var phoneNumber by remember { mutableStateOf(if (!isContactNew) currentContact.phone else "") }
        var age by remember { mutableIntStateOf(if (!isContactNew) currentContact.age else 0) }
        var email by remember { mutableStateOf(if (!isContactNew) currentContact.email else "") }
        var address by remember { mutableStateOf(if (!isContactNew) currentContact.address else "") }
        var favorite by remember { mutableStateOf(if (!isContactNew) currentContact.favorite else false) }

        val context = LocalContext.current

        InfoSection(
            name,
            { name = it },
            phoneNumber,
            { phoneNumber = it },
            age,
            { age = it },
            email,
            { email = it },
            address,
            { address = it },
            favorite,
            { favorite = it }
        )
        CloseSection(
            backToContacts,
            {
                var canAdd = false
                if (name.isEmpty()) {
                    showToast(context, "Il faut un nom de contact")
                } else if (phoneNumber.isEmpty()) {
                    showToast(context, "Il faut un numéro de téléphone")
                } else {
                    canAdd = true
                }


                if (isContactNew && canAdd) {
                    contactViewModel.add(
                        Contact(
                            0,
                            name,
                            phoneNumber,
                            email,
                            address,
                            age,
                            favorite,
                            0
                        )
                    )
                    showToast(context, "Ajouté $name aux contacts")
                    backToContacts()
                } else if (canAdd) {
                    contactViewModel.update(
                        Contact(
                            currentContact.uid,
                            name,
                            phoneNumber,
                            email,
                            address,
                            age,
                            favorite,
                            0
                        )
                    )
                    showToast(context, "$name sauvegardé")
                    backToContacts()
                }
            }
        )
    }
}

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
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PhotoSection(name)

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

@Composable
fun PhotoSection(name: String) {
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
                .background(MaterialTheme.colorScheme.onSurface),
            contentAlignment = Alignment.Center
        ) {
            if (name.isNotEmpty()) {
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
            Button(
                modifier = Modifier
                    .width(125.dp),
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

@Composable
fun CloseSection(backToContacts: () -> Unit, addToContacts: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(start = 100.dp, end = 100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Cancel",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 15.dp)
                    .clickable(onClick = {
                        backToContacts()
                    })
            )
            VerticalDivider(
                modifier = Modifier
                    .height(15.dp),
                color = Color.White,
                thickness = 2.dp
            )
            Text(
                "Save",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
                    .clickable(onClick = {
                        addToContacts()
                    })
            )
        }
    }
}