package com.example.jetpackrick.ui.character

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.ui.character.CharacterViewModel.Companion.CHARACTER_FETCH_ERROR_MESSAGE
import com.example.jetpackrick.ui.character.CharacterViewModel.Companion.CHARACTER_LOAD_MORE_ERROR_MESSAGE
import com.example.jetpackrick.ui.character.SampleCharacters.JETPACK_RICK_MOCK


@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<CharacterResponse>
) {
    Box(
        modifier = modifier
            .fillMaxSize()

    ) {

        Column {
            val featured: List<CharacterResponse> = characters.itemSnapshotList.take(10)
                .filterNotNull()
                .ifEmpty { listOf(JETPACK_RICK_MOCK) } // fallback

            FeaturedCharacters(featured, modifier)
        }

        when (val state = characters.loadState.refresh) {
            is LoadState.Loading -> {
                FullWidthSpinner()
            }

            is LoadState.Error -> {
                FullWidthError(
                    message = state.error.message ?: CHARACTER_FETCH_ERROR_MESSAGE,
                    onRetry = { characters.retry() }
                )
            }

            else -> Unit
        }

        when (val state = characters.loadState.append) {
            is LoadState.Loading -> {
                FullWidthSpinner()

            }

            is LoadState.Error -> {
                FullWidthError(
                    message = state.error.message ?: CHARACTER_LOAD_MORE_ERROR_MESSAGE,
                    onRetry = { characters.retry() }
                )
            }

            else -> Unit
        }


    }
}

@Composable
fun FullWidthSpinner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun FullWidthError(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message)
        Spacer(Modifier.height(8.dp))
        Button(onClick = onRetry) { Text("Retry") }
    }
}