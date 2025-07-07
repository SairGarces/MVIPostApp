package com.sairgarces.mvipostsapp.ui.main

sealed class MainIntent {
    object LoadPosts : MainIntent()
    object RefreshPosts : MainIntent()
}