package ua.kpi.comsys.iv7213

import com.beust.klaxon.Json
import java.io.Serializable

data class Movie (
    val title: String,
    val year: String,
    val imdbId: String,
    val type: String,
    val poster: String
): Serializable

data class MovieDetails(
    @Json("Title")
    val title: String,
    @Json("Year")
    val year: String,
    @Json("Rated")
    val rated: String,
    @Json("Released")
    val released: String,
    @Json("Runtime")
    val runtime: String,
    @Json("Genre")
    val genre: String,
    @Json("Director")
    val director: String,
    @Json("Writer")
    val writer: String,
    @Json("Actors")
    val actors: String,
    @Json("Plot")
    val plot: String,
    @Json("Language")
    val language: String,
    @Json("Country")
    val country: String,
    @Json("Awards")
    val awards: String,
    @Json("Poster")
    val poster: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,
    @Json("Type")
    val type: String,
    @Json("Production")
    val production: String
)