package com.example.listedecontacts.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "myTable")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val age: Int,
    val favorite: Boolean,
    val photo: ImageVector
)