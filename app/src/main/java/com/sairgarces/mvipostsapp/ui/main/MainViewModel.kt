package com.sairgarces.mvipostsapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sairgarces.mvipostsapp.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostRepository) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    fun handleIntent(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.LoadPosts -> fetchPosts()
                is MainIntent.RefreshPosts -> fetchPosts()
            }
        }
    }

    private suspend fun fetchPosts() {
        // Emitir estado de carga
        _state.value = _state.value.copy(isLoading = true, error = null)

        try {
            val posts = repository.getPosts()
            // Emitir estado de éxito
            _state.value = _state.value.copy(isLoading = false, posts = posts)
        } catch (e: Exception) {
            // Emitir estado de error
            _state.value = _state.value.copy(isLoading = false, error = e.message)
        }
    }
}
