package com.example.healthypleasureapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
//import com.example.timer.R.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.concurrent.timer

class TimerActivity : AppCompatActivity() {
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1

    lateinit var fab : FloatingActionButton
    lateinit var resetFab: FloatingActionButton
    lateinit var pauseFab: FloatingActionButton
    lateinit var hourTextView : TextView
    lateinit var minTextView : TextView
    lateinit var secTextView : TextView
    lateinit var milliTextView: TextView
    lateinit var labLayout : LinearLayout
    lateinit var labButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        fab = findViewById(R.id.fab)
        resetFab = findViewById(R.id.resetFab)
        pauseFab = findViewById(R.id.pauseFab)
        hourTextView = findViewById(R.id.hourTextView)
        minTextView = findViewById(R.id.minTextView)
        secTextView = findViewById(R.id.secTextView)
        milliTextView = findViewById(R.id.miilTextView)
        labLayout = findViewById(R.id.labLayout)
        labButton = findViewById(R.id.labButton)

        // 시작 버튼 클릭 시, 시작 함수
        fab.setOnClickListener{
            start()
        }

        // 기록 버튼 클릭 시, 기록
        labButton.setOnClickListener{
            recordLapTime()
        }

        // reset 버튼 클릭 시, reset 함수
        resetFab.setOnClickListener{
            reset()
        }

        pauseFab.setOnClickListener{
            pause()
        }
    }
    //리셋 버튼 멈춤 버튼 따로, 리셋을 앞에,
    // 멈춤 함수 -> 시작 버튼 보이게, pause 버튼은 리셋 버튼으로
    private fun pause(){

        fab.setVisibility(View.VISIBLE)
        resetFab.setVisibility(View.VISIBLE)
        pauseFab.setVisibility(View.INVISIBLE)
        labButton.setVisibility(View.INVISIBLE)

        timerTask?.cancel()
    }

    // 시작 함수 -> 시작 버튼 안보이게(기능은 O), time 계산
    private fun start(){
        fab.setVisibility(View.INVISIBLE)
        resetFab.setVisibility(View.INVISIBLE)
        pauseFab.setVisibility(View.VISIBLE)
        labButton.setVisibility(View.VISIBLE)

        timerTask = timer(period = 10){
            time++
            val hour = ( time / 100 ) / 3600
            val min = ( time / 100 ) / 60
            val sec = ( time / 100 )
            val milli = ( time % 100 )

            runOnUiThread{
                hourTextView.text = "$hour"
                minTextView.text = "$min"
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    // 기록 함수 -> 기록
    private fun recordLapTime(){
        val lapTime = this.time
        val textView = TextView(this)
        textView.setTextColor(Color.parseColor("#FF8585"));
        textView.text = "_______________________________________________________________" +
                "     $lap       ${ ( lapTime/ 100 ) / 360}:${(lapTime / 100)/60}:${( lapTime / 100 ) % 60}.${(lapTime % 100)}"


        // 맨 위 랩타임 추가
        labLayout.addView(textView, 0)
        lap++
    }

    // 리셋 함수 -> 초기화, 시작 버튼 보이게
    private fun reset(){
        //fab.setImageResource(R.drawable.ic_baseline_pause_24)
        timerTask?.cancel()

        fab.setVisibility(View.VISIBLE)

        time = 0
        isRunning = false
        //fab.setImageResource(drawable.ic_baseline_play_arrow_24)
        hourTextView.text = "0"
        minTextView.text = " 0"
        secTextView.text = " 0"
        milliTextView.text = "00"
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