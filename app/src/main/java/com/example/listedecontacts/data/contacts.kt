package com.example.listedecontacts.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.listedecontacts.R

@Entity (tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val name: String,
    val phone: String,
    val email: String,
    val address: String,
    val age: Int,
    val favorite: Boolean,
    val photo: String
)