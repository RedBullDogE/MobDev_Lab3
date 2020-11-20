package ua.kpi.comsys.iv7213

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import org.json.JSONArray
import org.json.JSONObject
import ua.kpi.comsys.iv7213.adapters.CustomMovieListAdapter
import ua.kpi.comsys.iv7213.adapters.ViewPagerAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.movieListView)

        val movies = parseJSON()

        listView.adapter = CustomMovieListAdapter(this, movies)
    }

    private fun parseJSON(): ArrayList<Movie> {
        val inputStream = assets.open("MoviesList.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val stringJSON = String(buffer, Charsets.UTF_8)

        val movies: ArrayList<Movie> = ArrayList()

        val moviesJSON: JSONArray = JSONObject(stringJSON).getJSONArray("Search")

        for (i in 0 until moviesJSON.length()) {
            val movieObj = moviesJSON.getJSONObject(i)
            val title = movieObj.getString("title")
            val year = movieObj.getString("year")
            val imdbId = movieObj.getString("imdbId")
            val type = movieObj.getString("type")
            val poster = movieObj.getString("poster")

            movies.add(Movie(title, year, imdbId, type, poster))
        }

        return movies
    }
}

