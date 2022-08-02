package com.example.healthypleasureapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LogoutActivity : AppCompatActivity() {

    lateinit var LogoutButton: Button
    lateinit var LogoutNoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        LogoutButton = findViewById(R.id.Logout_button)
        LogoutNoButton = findViewById(R.id.Logout_No_button)

        // logout 버튼 클릭 시
        // 자동로그인 기록 삭제 후, 로그아웃 하였다는 메시지 출력과 함께 MainActivity 로 넘어감
        LogoutButton.setOnClickListener {
            MySharedPreferences.clearUser(this)
            Toast.makeText(this, "로그아웃 하였습니다.", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        LogoutNoButton.setOnClickListener {
            val intent = Intent(this, Calendar_mainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}