package com.example.jetpackrick

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackrick.ui.character.CharacterDetailScreen
import com.example.jetpackrick.ui.character.CharacterScreen
import com.example.jetpackrick.ui.character.CharacterViewModel

@Composable
fun NavGraph(modifier: Modifier = Modifier, viewModel: CharacterViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "featured_characters") {
        composable("featured_characters") {
            val characters = viewModel.characters.collectAsLazyPagingItems()
            CharacterScreen(
                modifier = modifier,
                characters = characters,
                onCharacterClick = {character ->
                    viewModel.selectCharacter(character)
                    navController.navigate("character_detail")
                }
            )
        }

        composable("character_detail" ) {
            CharacterDetailScreen(
                modifier = modifier,
                character = viewModel.selectedCharacter!!,
                onBack = { navController.navigate("featured_characters") }
            )
        }
    }
}