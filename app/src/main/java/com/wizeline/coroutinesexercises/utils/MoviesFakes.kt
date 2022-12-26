package com.wizeline.coroutinesexercises.utils

import com.wizeline.coroutinesexercises.domain.entities.Movie
import java.time.LocalDate

object MoviesFakes {
    val avatar = Movie(
        id = "76600",
        title = "Avatar: The Way of Water",
        posterUrl = "https://image.tmdb.org/t/p/original/5ScPNT6fHtfYJeWBajZciPV3hEL.jpg",
        genreIds = emptyList(),
        overview = "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
        rating = 81,
        releaseDate = LocalDate.of(2022, 12, 14),
        runtimeMinutes = null,
    )
    val troll = Movie(
        id = "736526",
        title = "Troll",
        posterUrl = "https://image.tmdb.org/t/p/original/9z4jRr43JdtU66P0iy8h18OyLql.jpg",
        genreIds = emptyList(),
        overview = "Deep inside the mountain of Dovre, something gigantic awakens after being trapped for a thousand years. Destroying everything in its path, the creature is fast approaching the capital of Norway. But how do you stop something you thought only existed in Norwegian folklore?",
        rating = 67,
        releaseDate = LocalDate.of(2022, 12, 1),
        runtimeMinutes = null,
    )
    val blackAdam = Movie(
        id = "436270",
        title = "Black Adam",
        posterUrl = "https://image.tmdb.org/t/p/original/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
        genreIds = emptyList(),
        overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
        rating = 72,
        releaseDate = LocalDate.of(2022, 10, 19),
        runtimeMinutes = null,
    )
    val narnia = Movie(
        id = "411",
        title = "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe",
        posterUrl = "https://image.tmdb.org/t/p/original/clfyMMg00OiowO5uupKpVKjRwmV.jpg",
        genreIds = emptyList(),
        overview = "Siblings Lucy, Edmund, Susan and Peter step through a magical wardrobe and find the land of Narnia. There, they discover a charming, once peaceful kingdom that has been plunged into eternal winter by the evil White Witch, Jadis. Aided by the wise and magnificent lion, Aslan, the children lead Narnia into a spectacular, climactic battle to be free of the Witch's glacial powers forever.",
        rating = 71,
        releaseDate = LocalDate.of(2005, 12, 7),
        runtimeMinutes = null,
    )
    val pinocchio = Movie(
        id = "555604",
        title = "Guillermo del Toro's Pinocchio",
        posterUrl = "https://image.tmdb.org/t/p/original/vx1u0uwxdlhV2MUzj4VlcMB0N6m.jpg",
        genreIds = emptyList(),
        overview = "During the rise of fascism in Mussolini's Italy, a wooden boy brought magically to life struggles to live up to his father's expectations.",
        rating = 85,
        releaseDate = LocalDate.of(2022, 11, 9),
        runtimeMinutes = null,
    )
}