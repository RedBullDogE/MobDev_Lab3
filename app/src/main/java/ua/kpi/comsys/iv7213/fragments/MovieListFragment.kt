package ua.kpi.comsys.iv7213.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import ua.kpi.comsys.iv7213.Movie
import ua.kpi.comsys.iv7213.R
import ua.kpi.comsys.iv7213.activities.MovieAddActivity
import ua.kpi.comsys.iv7213.adapters.CustomMovieListAdapter


class MovieListFragment() : Fragment() {

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
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(view.context, MovieAddActivity::class.java)

            startActivityForResult(intent, 1)
        }

        val movies = parseJSON()

        listView.adapter = CustomMovieListAdapter(movies)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val newMovie: Movie = data!!.getSerializableExtra("RESULT") as Movie

                (view!!.findViewById<ListView>(R.id.movieListView).adapter as CustomMovieListAdapter).addMovie(newMovie)
            }
        }
    }
}