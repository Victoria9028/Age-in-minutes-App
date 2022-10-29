package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set on click listener for the select date button
        btnSelectDate.setOnClickListener {view ->
            clickSelectDate(view)   // Select date function
        }
    }

    // Select date function
    fun clickSelectDate(view: View) {

        // Variables
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        // Set on date listener for the calendar
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->

            // Show the date as day, month, year and start the calendar count from january as 1 not 0
            val showDateSelected = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            // Shows the date selected
            tvShowDateSelected.setText(showDateSelected)

            // Set's the date format and locale of the app
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            // Parse to a date object from a string
            val theDate = sdf.parse(showDateSelected)

            // Calculate the selected date and convert into minutes
            val selectedDateInMinutes = theDate!!.time / 60000

            // Get the current date
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            // Calculate current date to minutes
            val currentDateInMinutes = currentDate!!.time / 60000

            // Calculates the difference from current date to selected date in minutes
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            // Show the age in minutes
            tvShowAgeMinutes.setText(differenceInMinutes.toString())

        }
            // Display the calendar dialog
            , year
            , month
            , day)

        // Limits calendar to use of past dates not future ones
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}