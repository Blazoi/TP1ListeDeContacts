package com.example.listedecontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listedecontacts.ui.theme.ListeDeContactsTheme
import com.example.listedecontacts.TopBar
import com.example.listedecontacts.contacts.ContactList
import com.example.listedecontacts.createcontacts.CreateContact
import com.example.listedecontacts.data.Contact
import com.example.listedecontacts.data.ContactDao
import com.example.listedecontacts.data.ContactViewModel
import kotlinx.coroutines.flow.StateFlow

public val TAG = "DEBUGGING"

class MainActivity : ComponentActivity() {

    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListeDeContactsTheme {
                MainUI(contactViewModel)
            }
        }
    }
}

public var currentContact: Contact =
    Contact(
        0,
        "",
        "",
        "",
        "",
        0,
        false,
        ""
    )

public var isContactNew = true

enum class Screens {
    Contacts,
    CreateContact
}

@Composable
fun MainUI(contactViewModel: ContactViewModel) {

    var isEditing by remember { mutableStateOf(false) }
    var screen by remember { mutableStateOf(Screens.Contacts) }
    val contacts by contactViewModel.contacts.collectAsState()


    isEditing = screen == Screens.CreateContact

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                isEditing,
                {
                    screen = Screens.CreateContact
                    isContactNew = true
                    isEditing = true
                },
                {
                    if (isContactNew) {
                        screen = Screens.Contacts
                        isEditing = false
                    } else {
                        contactViewModel.delete(currentContact)
                        screen = Screens.Contacts
                        isEditing = false
                    }
                }
            )
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (screen == Screens.Contacts)
                ContactList(contacts, {
                    currentContact = it
                    isContactNew = false
                    screen = Screens.CreateContact
                })
            else
                CreateContact(
                    contactViewModel,
                    {
                        screen = Screens.Contacts
                    }
                )
        }
    }
}