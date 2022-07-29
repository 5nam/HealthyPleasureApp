package com.example.healthypleasureapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast

class Calendar_mainActivity : AppCompatActivity() {
    lateinit var calendarView : CalendarView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_main)
        calendarView = findViewById(R.id.calendarView)

        val userID = intent.getStringExtra("N_userID")

        //캘린더 날짜 텍스트 크기 변경
        calendarView.dateTextAppearance = com.google.android.material.R.style.TextAppearance_AppCompat_Large
        calendarView.weekDayTextAppearance = com.google.android.material.R.style.Base_TextAppearance_AppCompat_Large

        //현재 날짜를 운동내용리스트로 보냄
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val intent = Intent(this, Calendar_listActivity::class.java)
            intent.putExtra("N_year", year)
            intent.putExtra("N_month", month+1)
            intent.putExtra("N_day", dayOfMonth)
            intent.putExtra("N_userID", userID)
            startActivity(intent)
        }
    }
}