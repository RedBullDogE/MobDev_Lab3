package ua.kpi.comsys.iv7213.adapters

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ua.kpi.comsys.iv7213.Movie
import ua.kpi.comsys.iv7213.R
import ua.kpi.comsys.iv7213.activities.MovieDetailsActivity
import java.io.FileNotFoundException
import kotlin.collections.ArrayList

class CustomMovieListAdapter(private val movieList: ArrayList<Movie>) :
    BaseAdapter() {

    private var displayMovieList: ArrayList<Movie> = movieList.clone() as ArrayList<Movie>

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(parent!!.context)
            item = layoutInflater.inflate(R.layout.movie_item, parent, false)

            val title = item.findViewById<TextView>(R.id.movieTitle)
            val year = item.findViewById<TextView>(R.id.movieYear)
            val type = item.findViewById<TextView>(R.id.movieType)
            val poster = item.findViewById<ImageView>(R.id.moviePoster)
            val movieHolder = MovieHolder(title, year, type, poster)

            item.tag = movieHolder
        } else {
            item = convertView
        }

        val movieHolder = item.tag as MovieHolder
        movieHolder.title.text = displayMovieList[position].title
        movieHolder.year.text = displayMovieList[position].year
        movieHolder.type.text = displayMovieList[position].type

        try {
            val imageFile =
                parent!!.context.assets.open("Posters/${displayMovieList[position].poster}")
            val drawable: Drawable = Drawable.createFromStream(imageFile, null)
            movieHolder.poster.setImageDrawable(drawable)
        } catch (e: FileNotFoundException) {
            movieHolder.poster.setImageResource(R.mipmap.ic_launcher)
        }

        item.setOnClickListener {
            val intent = Intent(parent!!.context, MovieDetailsActivity::class.java)
            intent.putExtra("EXTRA_MOVIE_ID", displayMovieList[position].imdbId)

            item.context.startActivity(intent)
        }

        return item
    }

    private class MovieHolder(
        val title: TextView,
        val year: TextView,
        val type: TextView,
        val poster: ImageView
    )

    override fun getItem(position: Int): Any {
        return "Test string"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return displayMovieList.size
    }

    fun filter(query: String) {
        if (query.isNotEmpty()) {
            displayMovieList.clear()

            movieList.forEach {
                if (it.title.toLowerCase().contains(query.toLowerCase())) {
                    displayMovieList.add(it)
                }
            }
        } else {
            displayMovieList.clear()
            displayMovieList.addAll(movieList)
        }
        notifyDataSetChanged()
    }
}