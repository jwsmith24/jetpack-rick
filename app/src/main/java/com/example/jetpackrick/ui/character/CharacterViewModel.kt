package com.example.jetpackrick.ui.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackrick.data.CharacterListResponse
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterResponse>>(emptyList())
    val characters: StateFlow<List<CharacterResponse>> = _characters.asStateFlow()


    init {
        loadCharacters()
    }


    private fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _characters.value = repository.fetchAllCharacters()

                Log.d("JetpackRick status", "Loaded all characters!")
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

}