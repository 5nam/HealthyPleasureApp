package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView

class Calendar_mainActivity : AppCompatActivity() {
    lateinit var calendarView : CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_main)
        calendarView = findViewById(R.id.calendarView)

        //캘린더 날짜 텍스트 크기 변경//
        calendarView.dateTextAppearance = com.google.android.material.R.style.TextAppearance_AppCompat_Large

        //현재 날짜를 운동내용리스트로 보냄
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val intent = Intent(this, Calendar_listActivity::class.java)
            intent.putExtra("N_year", year)
            intent.putExtra("N_month", month+1)
            intent.putExtra("N_day", dayOfMonth)
            startActivity(intent)
        }
    }
}