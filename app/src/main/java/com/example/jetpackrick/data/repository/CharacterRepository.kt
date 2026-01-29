package com.example.jetpackrick.data.repository


import android.util.Log
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.data.network.JetpackRickApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// @Inject constructor === this class can be built by DI
class CharacterRepository @Inject constructor(
    private val api: JetpackRickApi // sets a property directly in the constructor
) {

    suspend fun fetchAllCharacters(): List<CharacterResponse> = withContext(Dispatchers.IO) {
        api.getAllCharacters().results.also {
            characters ->
            characters.forEach {
                Log.d("JetpackRICK look at me", "id=${it.id} name=${it.name}" )

            }
        }
    }



    suspend fun fetchCharacter(characterId: Int) = withContext(Dispatchers.IO) {
        api.getCharacter(characterId)
    }

}