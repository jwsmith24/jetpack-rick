package com.example.jetpackrick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackrick.ui.character.CharacterScreen
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
                    val characters = viewModel.characters.collectAsLazyPagingItems()

                    CharacterScreen(characters = characters, modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }

}