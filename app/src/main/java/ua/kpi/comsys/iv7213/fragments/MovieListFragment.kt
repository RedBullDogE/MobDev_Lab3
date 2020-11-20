package ua.kpi.comsys.iv7213.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import org.json.JSONArray
import org.json.JSONObject
import ua.kpi.comsys.iv7213.Movie
import ua.kpi.comsys.iv7213.R
import ua.kpi.comsys.iv7213.adapters.CustomMovieListAdapter


class MovieListFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.movieListView)

        val movies = parseJSON()

        listView.adapter = CustomMovieListAdapter(context!!, movies)
    }

    private fun parseJSON(): ArrayList<Movie> {
        val inputStream = context!!.assets.open("MoviesList.json")
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