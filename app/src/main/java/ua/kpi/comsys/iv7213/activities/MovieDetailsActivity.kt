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
        val viewsList: ArrayList<Int> = arrayListOf(
            R.id.movieTitleDetails,
            R.id.movieYearDetails,
            R.id.movieRated,
            R.id.movieReleased,
            R.id.movieRuntime,
            R.id.movieGenre,
            R.id.movieDirector,
            R.id.movieWriter,
            R.id.movieActors,
            R.id.moviePlot,
            R.id.movieLanguage,
            R.id.movieCountry,
            R.id.movieAwards,
            R.id.movieRating,
            R.id.movieVotes,
            R.id.movieID,
            R.id.movieTypeDetails,
            R.id.movieProduction
        )

        if (imdbID == "noid" || imdbID == null) {
            Toast.makeText(applicationContext, "Not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val result: MovieDetails? = parseJSON(imdbID)

        if (result == null) {
            Toast.makeText(applicationContext, "Something goes wrong", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val resultFields: ArrayList<String> = arrayListOf(
            result.title,
            result.year,
            result.rated,
            result.released,
            result.runtime,
            result.genre,
            result.director,
            result.writer,
            result.actors,
            result.plot,
            result.language,
            result.country,
            result.awards,
            result.imdbRating,
            result.imdbVotes,
            result.imdbID,
            result.type,
            result.production
        )

        try {
            val imageFile = assets.open("Posters/${result.poster}")
            val drawable: Drawable = Drawable.createFromStream(imageFile, null)
            findViewById<ImageView>(R.id.moviePosterDetails).setImageDrawable(drawable)
        } catch (e: FileNotFoundException) {

        }

        viewsList.forEachIndexed { index, element ->
            findViewById<TextView>(element).append(resultFields[index])
        }

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