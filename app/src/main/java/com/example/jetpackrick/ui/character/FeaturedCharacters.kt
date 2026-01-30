package com.example.jetpackrick.ui.character

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.jetpackrick.data.CharacterResponse


@Composable
fun FeaturedCharacters(characters: List<CharacterResponse>) {

    LazyRow {
        items(items = characters
            ) {
            character ->
            CharacterCard(character)
        }

    }
}