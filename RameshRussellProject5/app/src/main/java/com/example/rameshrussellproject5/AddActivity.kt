package com.example.rameshrussellproject5

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddActivity:AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val button = findViewById<Button>(R.id.add)
        val food = findViewById<EditText>(R.id.foodName)
        val calories =findViewById<EditText>(R.id.num)
        button.setOnClickListener {
            val health = Health(food =food.text.toString(), calories = calories.text.toString())
            //health.let { h ->
                lifecycleScope.launch(IO) {
                    (application as HealthApplication).db.healthDao().insert(
                        health
                    )
                }
            intent = Intent(this,MainActivity::class.java)
            this.startActivity(intent)

        }
    }
}