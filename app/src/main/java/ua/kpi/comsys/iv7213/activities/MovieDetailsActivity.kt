package ua.kpi.comsys.iv7213.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import ua.kpi.comsys.iv7213.MovieDetails
import ua.kpi.comsys.iv7213.R
import java.io.FileNotFoundException

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setupDetails()
    }

    private fun setupDetails() {
        val imdbID: String? = intent.getStringExtra("EXTRA_MOVIE_ID")

        if (imdbID == "noid" || imdbID == null) return

        val result: MovieDetails? = parseJSON(imdbID)

        if (result == null) {
            Toast.makeText(applicationContext, "Not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        try {
            val imageFile = assets.open("Posters/${result.poster}")
            val drawable: Drawable = Drawable.createFromStream(imageFile, null)
            findViewById<ImageView>(R.id.moviePosterDetails).setImageDrawable(drawable)

        } catch (e: FileNotFoundException) {

        }

        findViewById<TextView>(R.id.movieTitleDetails).append(result.title)
        findViewById<TextView>(R.id.movieYearDetails).append(result.year)
        findViewById<TextView>(R.id.movieRated).append(result.rated)
        findViewById<TextView>(R.id.movieReleased).append(result.released)
        findViewById<TextView>(R.id.movieRuntime).append(result.runtime)
        findViewById<TextView>(R.id.movieGenre).append(result.genre)
        findViewById<TextView>(R.id.movieDirector).append(result.director)
        findViewById<TextView>(R.id.movieWriter).append(result.writer)
        findViewById<TextView>(R.id.movieActors).append(result.actors)
        findViewById<TextView>(R.id.moviePlot).append(result.plot)
        findViewById<TextView>(R.id.movieLanguage).append(result.language)
        findViewById<TextView>(R.id.movieCountry).append(result.country)
        findViewById<TextView>(R.id.movieAwards).append(result.awards)
        findViewById<TextView>(R.id.movieRating).append(result.imdbRating)
        findViewById<TextView>(R.id.movieVotes).append(result.imdbVotes)
        findViewById<TextView>(R.id.movieID).append(result.imdbID)
        findViewById<TextView>(R.id.movieTypeDetails).append(result.type)
        findViewById<TextView>(R.id.movieProduction).append(result.production)
    }

    private fun parseJSON(imdbId: String): MovieDetails? {
        val inputStream = assets.open("Details/$imdbId.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val stringJSON = String(buffer, Charsets.UTF_8)

        return Klaxon()
            .parse<MovieDetails>(stringJSON)
    }
}