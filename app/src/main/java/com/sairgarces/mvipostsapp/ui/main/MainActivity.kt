package com.sairgarces.mvipostsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sairgarces.mvipostsapp.databinding.ActivityMainBinding
import com.sairgarces.mvipostsapp.ui.main.adapter.PostAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        // Enviar el primer intent para cargar los datos
        lifecycleScope.launch {
            mainViewModel.handleIntent(MainIntent.LoadPosts)
        }
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect { state ->
                render(state)
            }
        }
    }

    private fun render(state: MainState) {
        // Renderizar barra de progreso
        binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

        // Renderizar lista
        if (state.posts.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            postAdapter.submitList(state.posts)
        } else {
            binding.recyclerView.visibility = View.GONE
        }

        // Renderizar error
        if (state.error != null) {
            binding.errorTextView.visibility = View.VISIBLE
            binding.errorTextView.text = state.error
        } else {
            binding.errorTextView.visibility = View.GONE
        }
    }
}
