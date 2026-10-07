package com.example.listedecontacts.createcontacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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