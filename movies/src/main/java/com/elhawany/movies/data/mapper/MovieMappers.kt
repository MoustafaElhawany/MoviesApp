package com.elhawany.movies.data.mapper

import com.elhawany.core.utils.app.AppConfigurations.IMAGE_BASE_URL
import com.elhawany.movies.data.remote.dto.MovieDetailsResponse
import com.elhawany.movies.data.remote.dto.MovieDto
import com.elhawany.movies.domain.model.Movie
import com.elhawany.movies.domain.model.MovieDetails
import java.time.LocalDate

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = IMAGE_BASE_URL + poster_path,
        releaseYear = LocalDate.parse(release_date).year.toString(),
        rating = vote_average,
    )
}

fun MovieDetailsResponse.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        adult = adult,
        genres = genres.map { it.toGenre() },
        overview = overview,
        posterUrl = IMAGE_BASE_URL + poster_path,
        releaseYear = LocalDate.parse(release_date).year.toString(),
        title = title,
        rating = String.format("%.1f", vote_average).toDouble(),
        backdropUrl = backdrop_path?.let { IMAGE_BASE_URL + it }
    )
}

