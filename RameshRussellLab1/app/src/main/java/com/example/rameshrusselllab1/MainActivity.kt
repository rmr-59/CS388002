package com.example.rameshrusselllab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val textView =findViewById<TextView>(R.id.textView)
        var plus=1
        button.setOnClickListener{
            //Toast.makeText(it.context,"Clicked Button!",Toast.LENGTH_SHORT).show()
            counter+=plus
            textView.text= counter.toString()
            if(counter==100){
                button2.visibility= View.VISIBLE
            }
        }
        button2.setOnClickListener{
            plus++
            button2.visibility =View.INVISIBLE
        }
    }
    var counter=0
}