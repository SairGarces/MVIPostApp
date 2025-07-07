package com.sairgarces.mvipostsapp.data.repository

import com.sairgarces.mvipostsapp.data.network.ApiService

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts() = apiService.getPosts()
}