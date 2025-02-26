package com.example.rameshrussellproject4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide


class ActorAdapter(private val context: Context,private val actors:List<Actor>)
    :RecyclerView.Adapter<ActorAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //TODO("Not yet implemented")
        val view = LayoutInflater.from(context).inflate(R.layout.pics,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val actor = actors[position]
        holder.bind(actor)
    }

    override fun getItemCount() = actors.size

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
            private val actorImage = itemView.findViewById<ImageView>(R.id.actorPic)
            private val actorName = itemView.findViewById<TextView>(R.id.actorName)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(actor: Actor){
        actorName.text= actor.actorName
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500"+actor.actorPic)
            .into(actorImage)
        }
        override fun onClick(v: View?) {
            //TODO("Not yet implemented")
            val actor = actors[absoluteAdapterPosition]
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("Actor",actor)
            context.startActivity(intent)
        }

    }
}