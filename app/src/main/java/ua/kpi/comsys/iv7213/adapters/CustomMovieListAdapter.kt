package ua.kpi.comsys.iv7213.adapters

import android.content.Context
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

class CustomMovieListAdapter(private val movieList: ArrayList<Movie>) :
    BaseAdapter() {

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

            item.setOnClickListener {
                val intent = Intent(parent.context, MovieDetailsActivity::class.java)

                item.context.startActivity(intent)
            }
        } else {
            item = convertView
        }

        val movieHolder = item.tag as MovieHolder
        movieHolder.title.text = movieList[position].title
        movieHolder.year.text = movieList[position].year
        movieHolder.type.text = movieList[position].type

        try {
            val imageFile = parent!!.context.assets.open("Posters/${movieList[position].poster}")
            val drawable: Drawable = Drawable.createFromStream(imageFile, null)
            movieHolder.poster.setImageDrawable(drawable)
        } catch (e: FileNotFoundException) {

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
        return movieList.size
    }
}