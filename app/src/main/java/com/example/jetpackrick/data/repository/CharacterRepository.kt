package com.example.jetpackrick.data.repository


import android.util.Log
import com.example.jetpackrick.data.network.JetpackRickApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(
    private val api: JetpackRickApi
) {

    suspend fun fetchAllCharacters() = withContext(Dispatchers.IO) {
        api.getAllCharacters()
    }

    suspend fun fetchCharacter(characterId: Int) = withContext(Dispatchers.IO) {
        api.getCharacter(characterId)
    }

    suspend fun printCharacters() {
        val response = fetchAllCharacters()

        response.results.forEach { characterResponse ->
            Log.d("JetpackRICK look at me", "id=${characterResponse.id} name=${characterResponse.name}" )
        }

    }
}