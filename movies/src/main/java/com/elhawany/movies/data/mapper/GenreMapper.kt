package com.elhawany.movies.data.mapper

import com.elhawany.movies.data.remote.dto.GenreDto
import com.elhawany.movies.domain.model.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name,
    )
}



