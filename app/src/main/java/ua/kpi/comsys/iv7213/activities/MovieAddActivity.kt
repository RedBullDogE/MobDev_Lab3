package ua.kpi.comsys.iv7213.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ua.kpi.comsys.iv7213.Movie
import ua.kpi.comsys.iv7213.R


class MovieAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        findViewById<Button>(R.id.addMovieButton).setOnClickListener {
            addMovie()
        }
    }

    private fun addMovie() {
        val title = findViewById<EditText>(R.id.movieTitleForm).text.toString()
        val year = findViewById<EditText>(R.id.movieYearForm).text.toString()
        val type = findViewById<EditText>(R.id.movieTypeForm).text.toString()

        if (title == "") {
            Toast.makeText(
                applicationContext,
                "Please enter the title of the movie",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (year.toInt() < 1895 || year.toInt() > 2020) {
            Toast.makeText(
                applicationContext,
                "Please enter the correct release year",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (type == "") {
            Toast.makeText(
                applicationContext,
                "Please enter the type of the movie",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val resultIntent = Intent()
        resultIntent.putExtra("RESULT", Movie(title, year, "noid", type, ""))
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
