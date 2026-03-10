package com.example.jetpackrick.ui.character

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.ui.character.CharacterViewModel.Companion.FEATURED_CHARACTER_LIST_HEADER
import com.example.jetpackrick.ui.character.SampleCharacters.JETPACK_RICK_MOCK


@Composable
fun FeaturedCharacters(
    characters: List<CharacterResponse>,
    modifier: Modifier = Modifier,
    onCharacterClick: (CharacterResponse) -> Unit
) {

    Column(modifier = modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = FEATURED_CHARACTER_LIST_HEADER,
            color = Color(0xFF2EFF7A),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(size = 12.dp))
                .padding(8.dp)


        )

        HorizontalDivider(thickness = 2.dp, color = Color(0xFF2EFF7A), modifier = Modifier.padding(vertical = 6.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(
                items = characters
            ) { character ->
                CharacterCard(character, onCharacterClick)
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun FeaturedCharactersPreview() {
    FeaturedCharacters(listOf(JETPACK_RICK_MOCK, JETPACK_RICK_MOCK, JETPACK_RICK_MOCK), Modifier,  {})
}