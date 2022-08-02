package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FindIDresultActivity : AppCompatActivity() {

    lateinit var foundID:TextView
    lateinit var IDresult_findPW_button: Button
    lateinit var IDresult_login_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id_result)

        foundID = findViewById(R.id.foundID)
        IDresult_login_button = findViewById(R.id.Logout_No_button)
        IDresult_findPW_button = findViewById(R.id.Logout_button)

        // 찾은 id 받아와서 출력
        var intent = getIntent()
        var ID = intent.getStringExtra("foundID")

        foundID.text = ID

        IDresult_login_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        IDresult_findPW_button.setOnClickListener {
            val intent = Intent(this, FindPwActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}