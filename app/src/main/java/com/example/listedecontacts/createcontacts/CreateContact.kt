package com.example.listedecontacts.createcontacts

import com.example.listedecontacts.TAG
import android.util.Log
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
        var photo by remember { mutableStateOf(if (!isContactNew) currentContact.photo else "") }

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
            { favorite = it },
            photo,
            { photo = it }
        )
        CloseSection(
            backToContacts,
            {
                addToContacts(
                    contactViewModel,
                    context,
                    backToContacts,
                    name,
                    phoneNumber,
                    email,
                    address,
                    age,
                    favorite,
                    photo
                )
            }
        )
    }
}

fun addToContacts(
    contactViewModel: ContactViewModel,
    context: Context,
    backToContacts: () -> Unit,
    name: String,
    phoneNumber: String,
    email: String,
    address: String,
    age: Int,
    favorite: Boolean,
    photo: String
) {
    var canAdd = false
    if (name.isEmpty()) {
        showToast(context, "Il faut un nom de contact")
    } else if (phoneNumber.isEmpty()) {
        showToast(context, "Il faut un numéro de téléphone")
    } else {
        canAdd = true
    }

    Log.d(TAG, photo)


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
                photo
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
                photo
            )
        )
        showToast(context, "$name sauvegardé")
        backToContacts()
    }
}