package com.example.newsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.model.Source
import kotlinx.coroutines.launch

class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        newsAdapter = NewsAdapter(::onSourceClick)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = newsAdapter

        // Load the sources (example API key, replace with actual key)
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getSources("df5a35f38da84a4cb643c4b6d45ad543")
                if (response.isSuccessful) {
                    val sources = response.body()?.sources ?: emptyList()
                    newsAdapter.submitList(sources)
                } else {
                    Log.e("API_ERROR", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
            }
        }
    }

    private fun onSourceClick(source: Source) {
        val action = NewsListFragmentDirections.actionNewsListFragmentToSourceDetailFragment(source)
        findNavController().navigate(action)
    }
}
