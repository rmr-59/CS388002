package com.example.rameshrussellproject5

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class HealthFragment : Fragment() {
    private val healths = mutableListOf<DisplayHealth>()
    private val stats = intArrayOf(0,0,0)
    private lateinit var healthRecyclerView: RecyclerView
    private lateinit var healthAdapter: HealthAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun fetchData(view: View){
        val application: Application = requireActivity().application
        lifecycleScope.launch {
            (application as HealthApplication).db.healthDao().getAll().collect{ databaseList ->
                databaseList.map { entity ->
                    DisplayHealth(
                        entity.food,
                        entity.calories
                    )
                }.also { mappedList ->
                    healths.clear()
                    healths.addAll(mappedList)
                    healthAdapter.notifyDataSetChanged()

                }
            }
        }
        if(healths.isNotEmpty()){
            var max = healths[0].calories?.toInt()?: 0
            var min = healths[0].calories?.toInt()?: 0
            var total = 0
            for (x in healths ){
                var current = x.calories?.toInt()?: 0
                total += current
                if(current <min)
                    min= current
                if(current >min)
                    max = current
            }
            total /= healths.size
            stats[0]=total
            stats[1]= min
            stats[2]= max

        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_health, container, false)

        val layoutManager = LinearLayoutManager(context)
        healthRecyclerView=view.findViewById(R.id.healths)
        healthRecyclerView.layoutManager=layoutManager
        healthRecyclerView.setHasFixedSize(true)
        healthAdapter = HealthAdapter(view.context,healths)
        healthRecyclerView.adapter=healthAdapter
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            var intent = Intent(view.context,AddActivity::class.java)
            this.startActivity(intent)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchData(view)
    }

    companion object {

        fun newInstance(): HealthFragment {
                return HealthFragment()
            }
    }
}