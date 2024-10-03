package com.example.assignment111

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.*
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // connects values inputted by user to fun
        val text = findViewById<EditText>(R.id.input1)
        val interest = findViewById<EditText>(R.id.input2)
        var years = 0

        //access to spinner
        val yearMort = resources.getStringArray(R.array.yearMortgages)
        // gets spinner ID
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, yearMort)
            spinner.adapter = adapter
            // adds select listener
            spinner.onItemSelectedListener = object :

                // adapter is used for item selection listener
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    years = selectedItem.toInt()
                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + "" + yearMort[position], Toast.LENGTH_SHORT).show()
                }
                // nothing happens when nothing is selceted
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // setting up the submit button
        val button = findViewById<Button>(R.id.button1)
        // gives on click listener to the button
        button.setOnClickListener {

            // grabs the amount the user inputted
            val mortAmt = text.text.toString().toDouble()
            val interestRate = interest.text.toString().toDouble()
            val yearInt = (interestRate / 100 / 12)
            val numOfMonths = years * 12

            // divides the amount with the years selected from the spinner
            val monthPayment = mortAmt * ((yearInt * ((1 + yearInt).pow(numOfMonths))) / (((1 + yearInt).pow(numOfMonths)) - 1))

            // an intent to bring user to new page showing calculated results
            val intent = Intent(this@MainActivity, resultActivity::class.java)
            intent.putExtra("monthPayment", monthPayment.toString())
            startActivity(intent)

        }
    }
}