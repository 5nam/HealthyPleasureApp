package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FindPwActivity : AppCompatActivity() {
    lateinit var findPW_PW_button : Button
    lateinit var findPW_login_button: Button
    lateinit var findPW_ID_Edit : EditText
    lateinit var findPW_Phone_Edit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pw)

        findPW_PW_button = findViewById(R.id.findPW_PW_button)
        findPW_login_button = findViewById(R.id.findPW_login_button)
        findPW_ID_Edit = findViewById(R.id.findPW_ID_Edit)
        findPW_Phone_Edit = findViewById(R.id.findPW_Phone_Edit)

        findPW_PW_button.setOnClickListener {
            // 이름, 전화번호란이 비어있지 않은지
            if(findPW_ID_Edit.length() == 0) {
                Toast.makeText(this,"아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(findPW_Phone_Edit.length() == 0) {
                Toast.makeText(this,"전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                // 데이터베이스에 해당 아이디와 전화번호가 있는지와 알맞은지 체크
                if(findPW(findPW_ID_Edit.toString(), findPW_Phone_Edit.toString())) {
                    // 있다면, 그 키로 아이디 찾아서 화면에 띄어줌 : 근데, 여기서 띄우는 방법은 없을까?
                    // 왜냐하면 FindIDresultActivity에서 띄우면 한번 더 탐색하고 찾아야 하므로 아예 값을 넘겨주는 방법은 없나?
                    // https://haruple.tistory.com/170 : 넘겨주는 방법

                }
            }
        }

        findPW_login_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun findPW(id:String, phone:String) : Boolean {

        return true
    }
}