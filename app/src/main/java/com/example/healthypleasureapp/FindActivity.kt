package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FindActivity : AppCompatActivity() {
    lateinit var findIdButton: Button
    lateinit var findPwButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)

        findIdButton = findViewById<Button>(R.id.find_id_button)
        findPwButton = findViewById<Button>(R.id.find_pw_button)

        findIdButton.setOnClickListener {
            val intent = Intent(this, FindIdActivity::class.java)
            startActivity(intent)
        }

        findPwButton.setOnClickListener {
            val intent = Intent(this, FindPwActivity::class.java)
            startActivity(intent)
        }
    }
}