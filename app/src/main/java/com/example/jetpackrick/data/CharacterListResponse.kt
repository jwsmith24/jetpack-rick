package com.example.jetpackrick.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterListResponse(
    val info: Info,
    val results: List<CharacterResponse>
)

@JsonClass(generateAdapter = true)
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)


