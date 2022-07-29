package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var login_button: Button
    lateinit var signup_button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button = findViewById(R.id.home_login_button)
        signup_button = findViewById(R.id.home_signup_button)

        login_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        signup_button.setOnClickListener {
            // 회원가입창이 완성되면 그때 intent 로 화면 전환
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}