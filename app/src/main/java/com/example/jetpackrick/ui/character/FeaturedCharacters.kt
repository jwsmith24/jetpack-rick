package com.example.jetpackrick.ui.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.ui.character.SampleCharacters.JETPACK_RICK_MOCK


@Composable
fun FeaturedCharacters(
    characters: List<CharacterResponse>,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = "Featured Characters",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        LazyRow {
            items(
                items = characters
            ) { character ->
                CharacterCard(character)
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun FeaturedCharactersPreview() {
    FeaturedCharacters(listOf(JETPACK_RICK_MOCK, JETPACK_RICK_MOCK, JETPACK_RICK_MOCK))
}