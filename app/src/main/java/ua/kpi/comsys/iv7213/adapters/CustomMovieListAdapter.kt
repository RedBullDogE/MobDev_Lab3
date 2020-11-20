package ua.kpi.comsys.iv7213.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ua.kpi.comsys.iv7213.Movie
import ua.kpi.comsys.iv7213.R
import java.io.FileNotFoundException

class CustomMovieListAdapter(private val context: Context, private val movies: ArrayList<Movie>) :
    BaseAdapter() {

    private val movieList: ArrayList<Movie> = movies

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val item = layoutInflater.inflate(R.layout.movie_item, parent, false)


        item.findViewById<TextView>(R.id.movieTitle).text = movieList[position].title
        item.findViewById<TextView>(R.id.movieYear).text = movieList[position].year
        item.findViewById<TextView>(R.id.movieType).text = movieList[position].type

        try {
            val imageFile = context.assets.open("Posters/${movieList[position].poster}")
            val drawable: Drawable = Drawable.createFromStream(imageFile, null)
            item.findViewById<ImageView>(R.id.moviePoster).setImageDrawable(drawable)
        } catch (e: FileNotFoundException) {

        }

        return item
    }

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