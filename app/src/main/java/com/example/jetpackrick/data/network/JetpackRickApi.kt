package com.example.jetpackrick.data.network

import com.example.jetpackrick.data.CharacterResponse
import retrofit2.http.GET

interface JetpackRickApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}