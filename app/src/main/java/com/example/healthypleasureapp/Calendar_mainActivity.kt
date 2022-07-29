package com.example.healthypleasureapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // xml 을 이용한 메뉴 만들기
        return when(item?.itemId) {
            R.id.item_calendar -> {
                val intent = Intent(this, Calendar_mainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.item_stopwatch -> {
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.item_calculator -> {
                val intent = Intent(this, CalcActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.item_logout -> {
                val intent = Intent(this, LogoutActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}