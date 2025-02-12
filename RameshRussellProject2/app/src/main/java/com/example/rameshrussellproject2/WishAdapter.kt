package com.example.rameshrussellproject2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishAdapter (private val items:List<Wishlist>) :RecyclerView.Adapter<WishAdapter.ViewHolder>() {
class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
val name:TextView
val price:TextView
val url:TextView

init{
    name =itemView.findViewById(R.id.itemName)
    price = itemView.findViewById(R.id.itemPrice)
    url = itemView.findViewById(R.id.itemUrl)
    url.setOnClickListener{
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url.text.toString()))
            ContextCompat.startActivity(it.context, browserIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(it.context, "Invalid URL for " + url.id, Toast.LENGTH_LONG).show()
        }
    }
}

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val listView = inflater.inflate(R.layout.list_item,parent,false)
        return ViewHolder(listView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.name.text =item.name
        holder.price.text=item.price
        holder.url.text=item.url
    }

}