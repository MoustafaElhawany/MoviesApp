package com.elhawany.moviesapp.feature_movies.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elhawany.core.utils.hide
import com.elhawany.core.utils.show
import com.elhawany.core.utils.showSnackBar
import com.elhawany.movies.domain.model.Movie
import com.elhawany.moviesapp.databinding.FragmentMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment(), MoviesAdapter.Interaction {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUI()
    }

    private fun setupUI() {
        setUpRecyclerView()
        viewModel.getMoviesList()
    }

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter(interaction = this)
        binding.recyclerView.adapter = moviesAdapter
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

        viewModel.moviesList.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
        }
    }

    override fun onItemSelected(movie: Movie) {
        val action = MoviesListFragmentDirections
            .actionMoviesFragmentToMovieDetailsFragment(movie.id)
        findNavController().navigate(action)
    }
}