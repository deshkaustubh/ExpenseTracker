package com.example.expensetracker

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun formateDateToHumanReadableForm(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }
}