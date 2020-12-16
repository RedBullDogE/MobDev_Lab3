package ua.kpi.comsys.iv7213.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.kpi.comsys.iv7213.R

class DrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drawType = intent.getIntExtra("DRAW_TYPE", 2)

        if (drawType == 1)
            setContentView(R.layout.graph_page)
        else
            setContentView(R.layout.chart_page)
    }
}