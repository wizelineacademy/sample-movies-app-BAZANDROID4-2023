package com.wizeline.coroutinesexercises.utils.fakes

import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies

object GenresFakes {

    val genre1 = Genre(id = "12", "Adventure")
    val genre2 = Genre(id = "14", "Fantasy")
    val genre3 = Genre(id = "28", "Action")

    val genreAndMovies = listOf(
        GenreWithMovies(genre1, listOf(MoviesFakes.avatar, MoviesFakes.troll, MoviesFakes.narnia)),
        GenreWithMovies(
            genre2,
            listOf(
                MoviesFakes.avatar,
                MoviesFakes.troll,
                MoviesFakes.blackAdam,
                MoviesFakes.narnia,
                MoviesFakes.pinocchio
            )
        ),
        GenreWithMovies(
            genre3,
            listOf(MoviesFakes.avatar, MoviesFakes.troll, MoviesFakes.blackAdam)
        )
    )
}