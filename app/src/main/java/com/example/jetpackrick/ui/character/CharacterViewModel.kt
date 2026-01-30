package com.example.jetpackrick.ui.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackrick.data.network.Result
import com.example.jetpackrick.data.CharacterResponse
import com.example.jetpackrick.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Result<List<CharacterResponse>>>(Result.Loading)
    val state: StateFlow<Result<List<CharacterResponse>>> = _state.asStateFlow()


    init {
        loadCharacters()
    }


    private fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            // loading
            _state.value = Result.Loading
            Log.d("Jetpack Status", "Loading..")

            delay(2000) // sim loading

            try {

                val loaded = repository.fetchAllCharacters()
                _state.value = Result.Success(loaded)
                Log.d("JetpackRick status", "Loaded all characters!")
                loaded.forEach {
                    character ->
                    Log.d("Jetpack Character", "id=${character.id} name=${character.name}")
                }


            } catch (exception: Exception) {
                _state.value = Result.Error(exception)
                Log.e("Jetpack Character", "Error: ${exception.message}", exception)
            }
        }
    }

    companion object {
        const val CHARACTER_LIST_HEADER = "Characters"
    }

}