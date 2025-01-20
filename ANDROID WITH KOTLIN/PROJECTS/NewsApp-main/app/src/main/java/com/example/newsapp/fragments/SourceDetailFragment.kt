package com.example.newsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.model.Source
import kotlinx.coroutines.launch

class SourceDetailFragment : Fragment(R.layout.fragment_source_detail) {

    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find and initialize views safely
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val urlTextView: TextView = view.findViewById(R.id.urlTextView)
     //   val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        // Initialize the RecyclerView and Adapter
        newsAdapter = NewsAdapter { sourceId ->
            Log.d("Source Clicked", "Source ID: $sourceId")
        }
       // recyclerView.layoutManager = LinearLayoutManager(context)
       // recyclerView.adapter = newsAdapter

        // Get the source passed as an argument
        val source = arguments?.getParcelable<Source>("source")
        if (source != null) {
            displaySourceDetails(source, titleTextView, descriptionTextView, urlTextView)
        } else {
            Log.e("SourceDetailFragment", "Source argument is missing!")
        }

        // Fetch additional source details using Retrofit
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getSources("df5a35f38da84a4cb643c4b6d45ad543")
                if (response.isSuccessful) {
                    val sources = response.body()?.sources ?: emptyList()
                    Log.d("API_SUCCESS", "Fetched sources: $sources")
                    newsAdapter.submitList(sources)
                } else {
                    Log.e("API_ERROR", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
            }
        }
    }

    private fun displaySourceDetails(
        source: Source,
        titleTextView: TextView,
        descriptionTextView: TextView,
        urlTextView: TextView
    ) {
        // Display source details
        titleTextView.text = source.name ?: "Unknown Source"
        descriptionTextView.text = source.description ?: "No description available"
        urlTextView.text = source.url ?: "No URL available"
    }
}
