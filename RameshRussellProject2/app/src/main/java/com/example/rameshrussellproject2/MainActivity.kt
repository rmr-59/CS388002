package com.example.rameshrussellproject2

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.WithHint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    var shoplist : List<Wishlist> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val item = findViewById<TextInputEditText>(R.id.item)
        val price = findViewById<TextInputEditText>(R.id.price)
        val url = findViewById<TextInputEditText>(R.id.url)
        val wishRv= findViewById<RecyclerView>(R.id.wishView)
        val adapter = WishAdapter(shoplist)
        wishRv.adapter =adapter
        wishRv.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.button).setOnClickListener {
            val itemText = item.text.toString()
            val itemPrice = price.text.toString()
            val itemUrl = url.text.toString()
            (shoplist as MutableList<Wishlist>).add(Wishlist(itemText,itemPrice,itemUrl))
            adapter.notifyDataSetChanged()
        }
    }
}