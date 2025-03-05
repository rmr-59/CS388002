package com.example.rameshrussellproject5

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rameshrussellproject5.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val healths = mutableListOf<DisplayHealth>()
    private lateinit var healthRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val healthAdapter = HealthAdapter(this,healths)
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
        healthRecyclerView=findViewById(R.id.healths)
        healthRecyclerView.adapter=healthAdapter
        healthRecyclerView.layoutManager=LinearLayoutManager(this)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            intent = Intent(this,AddActivity::class.java)
            this.startActivity(intent)
        }
    }
}