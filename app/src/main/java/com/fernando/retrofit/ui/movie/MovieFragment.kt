package com.fernando.retrofit.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.fernando.retrofit.R
import com.fernando.retrofit.core.Resource
import com.fernando.retrofit.data.model.Movie
import com.fernando.retrofit.data.remote.MovieDataSource
import com.fernando.retrofit.databinding.FragmentMovieBinding
import com.fernando.retrofit.presentation.MovieViewModel
import com.fernando.retrofit.presentation.MovieViewModelFactory
import com.fernando.retrofit.repository.MovieRepositoryImpl
import com.fernando.retrofit.repository.RetrofitClient
import com.fernando.retrofit.ui.movie.adapters.MovieAdapter
import com.fernando.retrofit.ui.movie.adapters.concat.PopularConcatAdapter
import com.fernando.retrofit.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.fernando.retrofit.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(RetrofitClient.webservice)
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { res ->
            when (res) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    res.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    res.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    res.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${res.exception}")
                }
            }
        })

/*        viewModel.fetchPlants().observe(viewLifecycleOwner, Observer { res ->
            when(res) {
                is Resource.Loading -> {
                    Log.d("Genesys", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("Genesys", "Resultado: $res")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${res.exception}")
                }
            }
        })*/

    }


    override fun onMovieClick(movie: Movie) {
        Log.d("Movie", "onMovieClick: $movie")
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }

}