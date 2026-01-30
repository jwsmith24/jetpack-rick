package com.example.jetpackrick.ui.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpackrick.data.network.Result
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

   val characters : Flow<PagingData<CharacterResponse>> =
       repository.getCharactersPaged()
           .cachedIn(viewModelScope)


    companion object {
        const val CHARACTER_LIST_HEADER = "Characters"
        const val CHARACTER_FETCH_ERROR_MESSAGE = "Error loading characters.."
        const val CHARACTER_LOAD_MORE_ERROR_MESSAGE = "Error loading addition characters.."
    }

}