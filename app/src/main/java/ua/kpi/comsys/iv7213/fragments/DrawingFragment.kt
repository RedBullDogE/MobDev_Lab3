package ua.kpi.comsys.iv7213.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ua.kpi.comsys.iv7213.R
import ua.kpi.comsys.iv7213.activities.DrawActivity
import ua.kpi.comsys.iv7213.activities.MovieAddActivity


class DrawingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val graphBtn = view.findViewById<Button>(R.id.graphButton)
        val chartBtn = view.findViewById<Button>(R.id.chartButton)

        graphBtn.setOnClickListener {
            val intent = Intent(view.context, DrawActivity::class.java)
            intent.putExtra("DRAW_TYPE", 1)
            startActivity(intent)
        }

        chartBtn.setOnClickListener {
            val intent = Intent(view.context, DrawActivity::class.java)
            intent.putExtra("DRAW_TYPE", 2)
            startActivity(intent)
        }
    }
}