package com.example.jetpackrick.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {

    val characters: Flow<PagingData<CharacterResponse>> =
        repository.getCharactersPaged()
            .cachedIn(viewModelScope)


    companion object {
        const val FEATURED_CHARACTER_LIST_HEADER = "Featured Characters"
        const val CHARACTER_FETCH_ERROR_MESSAGE = "Error loading characters.."
        const val CHARACTER_LOAD_MORE_ERROR_MESSAGE = "Error loading additional characters.."
    }

}