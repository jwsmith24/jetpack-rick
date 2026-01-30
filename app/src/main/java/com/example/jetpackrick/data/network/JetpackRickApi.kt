package com.example.jetpackrick.data.network

import com.example.jetpackrick.data.CharacterListResponse
import com.example.jetpackrick.data.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JetpackRickApi {


    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") characterId: Int): CharacterResponse
}