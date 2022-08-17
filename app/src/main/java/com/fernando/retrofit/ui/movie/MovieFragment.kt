package com.fernando.retrofit.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.fernando.retrofit.R
import com.fernando.retrofit.core.Resource
import com.fernando.retrofit.data.remote.MovieDataSource
import com.fernando.retrofit.databinding.FragmentMovieBinding
import com.fernando.retrofit.presentation.MovieViewModel
import com.fernando.retrofit.presentation.MovieViewModelFactory
import com.fernando.retrofit.repository.MovieRepositoryImpl
import com.fernando.retrofit.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, Observer { res ->
            when (res) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${res.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${res.exception}")
                }
            }
        })

    }

}