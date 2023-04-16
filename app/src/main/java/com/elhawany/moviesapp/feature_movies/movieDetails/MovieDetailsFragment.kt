package com.elhawany.moviesapp.feature_movies.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.elhawany.core.utils.hide
import com.elhawany.core.utils.show
import com.elhawany.core.utils.showSnackBar
import com.elhawany.movies.domain.model.MovieDetails
import com.elhawany.moviesapp.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUI()
    }

    private fun setupUI() {
        val movieId = args.movieId
        viewModel.getMovieDetailsById(movieId)
    }

    private fun setupObservers() {
        viewModel.uiError.observe(viewLifecycleOwner) {
            val message = it.text.asString(requireContext())
            binding.root.showSnackBar(message)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.apply {
                if (isLoading) show()
                else hide()
            }
        }

        viewModel.movieDetails.observe(viewLifecycleOwner, ::displayMovieDetails)
    }

    private fun displayMovieDetails(movieDetails: MovieDetails) {
        movieDetails.backdropUrl?.let {
            Glide.with(requireContext())
                .load(it)
                .into(binding.ivBackdropImage)
        }
        Glide.with(requireContext())
            .load(movieDetails.posterUrl)
            .into(binding.ivPosterImage)
        binding.tvName.text = movieDetails.title
        binding.tvRating.text = movieDetails.rating.toString()
        binding.tvReleaseYear.text = movieDetails.releaseYear
        binding.tvDescription.text = movieDetails.overview
    }
}