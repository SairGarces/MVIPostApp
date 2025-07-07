package com.sairgarces.mvipostsapp.data.network

import com.sairgarces.mvipostsapp.data.model.Post
import retrofit2.http.GET


interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
