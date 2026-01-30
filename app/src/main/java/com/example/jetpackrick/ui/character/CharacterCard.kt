package com.example.jetpackrick.ui.character

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.data.Location
import com.example.jetpackrick.data.Origin
import com.example.jetpackrick.ui.character.SampleCharacters.JETPACK_RICK_MOCK
import androidx.compose.ui.Alignment
@Composable
fun CharacterCard(
    character: CharacterResponse
) {
    Column (modifier = Modifier.padding(16.dp)){
        Box {

            AsyncImage(
                model = character.image,
                contentDescription = "${character.name} avatar",
                modifier = Modifier
                    .width(140.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    )

                ,
                contentScale = ContentScale.Crop
            )
            Text(text = "hi",
                modifier = Modifier.align(Alignment.BottomStart)
                )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = character.name
            )
        Text(text = character.species)


    }

}


@Preview()
@Composable
fun CharacterCardPreview() {

    CharacterCard(JETPACK_RICK_MOCK)
}

object SampleCharacters {
    val JETPACK_RICK_MOCK = CharacterResponse(
        id = 99,
        name = "Jetpack Rick",
        status = "",
        species = "Human",
        type = "Type",
        gender = "Male",
        origin = Origin(),
        location = Location(),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(),
        url = "supercoolurl.com",
        created = "2025-10-10"


    )
}