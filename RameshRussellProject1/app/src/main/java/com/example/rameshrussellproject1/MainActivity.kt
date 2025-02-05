package com.example.rameshrussellproject1

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
        val guess1= findViewById<TextView>(R.id.guess1)
        val guess2= findViewById<TextView>(R.id.guess2)
        val guess3= findViewById<TextView>(R.id.guess3)
        val check1 = findViewById<TextView>(R.id.check1)
        val check2 = findViewById<TextView>(R.id.check2)
        val check3 = findViewById<TextView>(R.id.check3)
        val word = findViewById<TextView>(R.id.word)
        val input = findViewById<TextInputEditText>(R.id.input)
        val button = findViewById<Button>(R.id.button)
        val reset = findViewById<Button>(R.id.reset)
        val guesses = arrayOf(guess1,guess2,guess3)
        val checks = arrayOf(check1,check2,check3)
        var guess =0
        word.setText(wordToGuess)
        button.setOnClickListener{
            if(input.text.toString().length!=4 || !isAlph(input.text.toString().uppercase()) )
                Toast.makeText(this,"Not Valid",Toast.LENGTH_SHORT).show()
            else {
                if (guess < 3) {
                    guesses[guess].setText(input.text.toString().uppercase())
                    checks[guess].setText(checkGuess(input.text.toString().uppercase()))
                    guess++
                }
                if (guess == 3) {
                    word.visibility = View.VISIBLE
                    button.visibility = View.INVISIBLE
                    reset.visibility = View.VISIBLE
                }
            }
        }
        reset.setOnClickListener{
            recreate()
        }



    }
    val alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private fun isAlph(word:String) :Boolean{
        for (x in 0..3){
            if(word[x] !in alph)
                return false
        }
        return true
    }
    var wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}