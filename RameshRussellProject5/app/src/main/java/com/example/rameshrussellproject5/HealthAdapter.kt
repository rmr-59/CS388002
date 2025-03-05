package com.example.rameshrussellproject5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HealthAdapter(private val context: Context, private val healths: List<DisplayHealth>):
RecyclerView.Adapter<HealthAdapter.ViewHolder>(){
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        override fun onClick(v: View?) {
            //TODO("Not yet implemented")
        }
        private val food = itemView.findViewById<TextView>(R.id.food)
        private val calories = itemView.findViewById<TextView>(R.id.calories)
        init{
            itemView.setOnClickListener(this)
        }
        fun bind(health: DisplayHealth){
            food.text=health.food
            calories.text=health.calories
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.health_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount()= healths.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val health =healths[position]
        holder.bind(health)
    }
}