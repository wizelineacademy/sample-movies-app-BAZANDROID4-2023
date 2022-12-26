package com.wizeline.coroutinesexercises.utils

import com.wizeline.coroutinesexercises.domain.entities.Movie

object MoviesFakes {
    val avatar = Movie(
        id = "76600",
        title = "Avatar: The Way of Water",
        posterUrl = "https://image.tmdb.org/t/p/original/5ScPNT6fHtfYJeWBajZciPV3hEL.jpg",
    )
    val troll = Movie(
        id = "736526",
        title = "Troll",
        posterUrl = "https://image.tmdb.org/t/p/original/9z4jRr43JdtU66P0iy8h18OyLql.jpg",
    )
    val blackAdam = Movie(
        id = "436270",
        title = "Black Adam",
        posterUrl = "https://image.tmdb.org/t/p/original/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
    )
    val narnia = Movie(
        id = "411",
        title = "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe",
        posterUrl = "https://image.tmdb.org/t/p/original/clfyMMg00OiowO5uupKpVKjRwmV.jpg",
    )
    val pinocchio = Movie(
        id = "555604",
        title = "Guillermo del Toro's Pinocchio",
        posterUrl = "https://image.tmdb.org/t/p/original/vx1u0uwxdlhV2MUzj4VlcMB0N6m.jpg",
    )
}