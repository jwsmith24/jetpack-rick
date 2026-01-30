package com.example.jetpackrick.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackrick.data.CharacterResponse

class CharacterPagingSource (private val api: JetpackRickApi): PagingSource<Int, CharacterResponse>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null

        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        return try {
            val page = params.key ?: 1
            val response = api.getAllCharacters(page)

            val nextKey = if (response.info.next == null) null else page + 1
            val prevKey = if (page == 1) null else page -1

            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch(exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}