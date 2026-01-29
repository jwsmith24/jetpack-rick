package com.example.jetpackrick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackrick.data.network.Result
import com.example.jetpackrick.ui.character.CharacterViewModel
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
                    val state by viewModel.state.collectAsState(initial = Result.Loading)

                    when (val s = state) {
                        is Result.Loading -> {
                            Box (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                contentAlignment = Alignment.Center
                            ){
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(innerPadding
                                    ))
                            }

                        }
                        is Result.Error -> {
                            Text(
                                text = "Error: ${s.exception.message}"
                            )
                        }
                        is Result.Success -> {
                            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                                items(s.data) {
                                    character ->
                                    Text(character.name)
                                }
                            }
                        }
                    }
                }
            }
        }
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