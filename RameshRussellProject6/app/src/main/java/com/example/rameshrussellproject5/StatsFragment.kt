package com.example.rameshrussellproject5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class StatsFragment(private val stats: IntArray) : Fragment() {

    private val healths = mutableListOf<DisplayHealth>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_stats, container, false)

        val button = view.findViewById<Button>(R.id.button2)
        val avg= view.findViewById<TextView>(R.id.avg)
        val min= view.findViewById<TextView>(R.id.min)
        val max= view.findViewById<TextView>(R.id.max)
        if(stats.isNotEmpty()) {
            avg.text = stats[0].toString()
            min.text = stats[1].toString()
            max.text = stats[2].toString()
        }

        button.setOnClickListener {
            var intent = Intent(view.context,AddActivity::class.java)
            this.startActivity(intent)
        }
        return view
    }

    private fun calculate() {


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated

    }

    companion object {

        fun newInstance() : StatsFragment{
                return StatsFragment(intArrayOf(0,0,0))
            }
    }
}