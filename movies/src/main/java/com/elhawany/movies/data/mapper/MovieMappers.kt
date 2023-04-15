package com.elhawany.movies.data.mapper

import com.elhawany.movies.data.remote.dto.MovieDto
import com.elhawany.movies.domain.model.Movie

fun List<MovieDto>.toMovies(): List<Movie> {
    return this.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            adult = it.adult,
            backdropPath = it.backdrop_path,
            genreIds = it.genre_ids,
            originalLanguage = it.original_language,
            originalTitle = it.original_title,
            popularity = it.popularity,
            posterPath = it.poster_path,
            releaseDate = it.release_date,
            video = it.video,
            voteAverage = it.vote_average,
            voteCount = it.vote_count
        )
    }
}
