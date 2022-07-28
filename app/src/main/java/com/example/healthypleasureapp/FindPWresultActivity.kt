package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FindPWresultActivity : AppCompatActivity() {

    lateinit var foundPW: TextView
    lateinit var PWresult_login_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pw_result)

        foundPW = findViewById(R.id.foundPW)
        PWresult_login_button = findViewById(R.id.PWresult_login_button)


        var intent = getIntent()
        var PW = intent.getStringExtra("foundPW")

        foundPW.text = PW

        PWresult_login_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}