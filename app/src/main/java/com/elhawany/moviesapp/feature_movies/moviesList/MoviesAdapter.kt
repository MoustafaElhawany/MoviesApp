package com.elhawany.moviesapp.feature_movies.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elhawany.movies.domain.model.Movie
import com.elhawany.moviesapp.databinding.MovieItemBinding

class MoviesAdapter(
    private val interaction: Interaction? = null
) : ListAdapter<Movie, MoviesAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding, interaction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val itemBinding: MovieItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie) {
            itemBinding.tvName.text = movie.title
            itemBinding.tvReleaseYear.text = movie.releaseYear
            Glide.with(itemBinding.root.context)
                .load( movie.posterUrl)
                .into(itemBinding.ivPoster)
            itemBinding.root.setOnClickListener { interaction?.onItemSelected(movie) }
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface Interaction {
        fun onItemSelected(movie: Movie)
    }
}