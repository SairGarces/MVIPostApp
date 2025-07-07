package com.sairgarces.mvipostsapp.ui.main

import com.sairgarces.mvipostsapp.data.model.Post

data class MainState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)