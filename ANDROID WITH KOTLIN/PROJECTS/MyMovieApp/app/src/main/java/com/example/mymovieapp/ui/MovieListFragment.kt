package com.example.mymovieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.MovieAdapter
import com.example.mymovieapp.api.MovieApiService
import com.example.mymovieapp.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val movieApi = MovieApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchMovies()
        return view
    }

    private fun fetchMovies() {
        movieApi.getPopularMovies("8e3cc2feab2aad37e9370971ba1b4821")
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val movies = response.body()?.results ?: emptyList()

                      //  recyclerView.adapter=MovieAdapter(movies)
                        recyclerView.adapter = MovieAdapter(movies) { selectedMovie ->
                            val action = MovieListFragmentDirections
                                .actionMovieListFragmentToMovieDetailFragment(selectedMovie)
                            recyclerView.adapter?.notifyDataSetChanged()
                            findNavController().navigate(action)
                        }
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Failed to fetch movies", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

}