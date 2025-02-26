package com.example.rameshrussellproject4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity:AppCompatActivity() {
    private lateinit var actorImage:ImageView
    private lateinit var movieImage: ImageView
    private lateinit var actorName: TextView
    private lateinit var movieName: TextView
    private lateinit var movieDescription: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        actorImage = findViewById(R.id.actor)
        movieImage = findViewById(R.id.moviePic)
        actorName = findViewById(R.id.name)
        movieName = findViewById(R.id.movieName)
        movieDescription = findViewById(R.id.movieDesc)

        val actor = intent.getSerializableExtra("Actor",Actor::class.java)
        val movie = actor?.known
        actorName.text=actor?.actorName
        if(movie?.get(0)?.movieName==null)
            movieName.text= "Known For: " + movie?.get(0)?.movieTitle
        else
            movieName.text= "Known For: " + movie[0].movieName
        movieDescription.text=movie?.get(0)?.movieDesc
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+actor?.actorPic)
            .into(actorImage)

    Glide.with(this)
    .load("https://image.tmdb.org/t/p/w500"+movie?.get(0)?.moviePic)
    .into(movieImage)
}
}