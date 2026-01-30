package com.example.jetpackrick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.jetpackrick.data.network.Result
import com.example.jetpackrick.ui.character.CharacterViewModel
import com.example.jetpackrick.ui.character.CharacterViewModel.Companion.CHARACTER_FETCH_ERROR_MESSAGE
import com.example.jetpackrick.ui.character.CharacterViewModel.Companion.CHARACTER_LIST_HEADER
import com.example.jetpackrick.ui.character.CharacterViewModel.Companion.CHARACTER_LOAD_MORE_ERROR_MESSAGE
import com.example.jetpackrick.ui.theme.JetpackRickTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            JetpackRickTheme {


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: CharacterViewModel = hiltViewModel()
                    val characters = viewModel.characters.collectAsLazyPagingItems()

                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ){

                        LazyColumn {
                            items(
                                count = characters.itemCount,
                                key = characters.itemKey { it.id }
                            ) {
                                index ->
                                val character = characters[index] ?: return@items
                                Text(
                                    text=character.name,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }


                    when (val state = characters.loadState.refresh) {
                        is LoadState.Loading -> {
                            FullWidthSpinner()
                        }

                        is LoadState.Error -> {
                            FullWidthError(
                                message = state.error.message ?: CHARACTER_FETCH_ERROR_MESSAGE,
                                onRetry = {characters.retry()}
                            )
                        }

                        else -> Unit
                    }

                        when (val state = characters.loadState.append) {
                            is LoadState.Loading ->  {
                                FullWidthSpinner()

                            }

                            is LoadState.Error -> {
                                FullWidthError(
                                    message = state.error.message ?: CHARACTER_LOAD_MORE_ERROR_MESSAGE,
                                    onRetry = {characters.retry()}
                                )
                            }

                            else -> Unit
                        }


                    }
                }
            }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackRickTheme {
        Greeting("Android")
    }
}