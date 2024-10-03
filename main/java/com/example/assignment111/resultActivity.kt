package com.example.assignment111

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class resultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result = findViewById<TextView>(R.id.result2)
        var final = intent.getStringExtra("monthPayment")
        result.text = ("$" + final + " / Month")

        val button = findViewById<Button>(R.id.returnButton)
        button.setOnClickListener {
            val intent = Intent(this@resultActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}