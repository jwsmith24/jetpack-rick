package com.example.jetpackrick.ui.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jetpackrick.data.CharacterResponse

@Composable
fun CharacterDetailScreen(
    character: CharacterResponse,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector =Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Button",
                tint = Color(0xFF2EFF7A),
                modifier = Modifier.size(64.dp)
            )

        }

        character.let { // not really doing anything because character is not nullable, but why not use fancy kotlin
            // if it were nullable, everything in this block would automatically be smart casted to not null so we don't need a ton of ?. etc
            AsyncImage(model = it.image, contentDescription = it.name, modifier.padding(vertical = 8.dp))
            Text(it.name, style = MaterialTheme.typography.headlineMedium)
            Text("Status: ${it.status}")
            Text("Gender: ${it.gender}")
            Text("Origin: ${it.origin.name}")
            Text("Species: ${it.species}")
            Text("Type: ${if (it.type == "") "N/A" else it.type}")
            Text("Current Location: ${it.location.name}")

            val episodeText = if (it.episode.size == 1) "episode" else "episodes"
            Text("Appears in ${it.episode.size} $episodeText")

        }
    }
}