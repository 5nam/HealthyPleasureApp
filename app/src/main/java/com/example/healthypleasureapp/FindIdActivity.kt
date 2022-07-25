package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FindIdActivity : AppCompatActivity() {
    lateinit var findID_name_Edit: EditText
    lateinit var findID_Phone_Edit:EditText
    lateinit var findID_ID_button: Button
    lateinit var findID_PW_button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id)

        findID_name_Edit = findViewById(R.id.findID_name_Edit)
        findID_Phone_Edit = findViewById(R.id.findID_Phone_Edit)
        findID_ID_button = findViewById(R.id.findID_ID_button)
        findID_PW_button = findViewById(R.id.findID_PW_button)

        findID_ID_button.setOnClickListener {
            // 이름, 전화번호란이 비어있지 않은지
            if(findID_name_Edit.length() == 0) {
                Toast.makeText(this,"이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(findID_Phone_Edit.length() == 0) {
                Toast.makeText(this,"전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                // 데이터베이스에 해당 이름과 비밀번호가 있는지와 알맞은지 체크
                if(findID(findID_name_Edit.toString(), findID_Phone_Edit.toString())) {
                    // 있다면, 그 키로 아이디 찾아서 화면에 띄어줌 : 근데, 여기서 띄우는 방법은 없을까?
                    // 왜냐하면 FindIDresultActivity에서 띄우면 한번 더 탐색하고 찾아야 하므로 아예 값을 넘겨주는 방법은 없나?
                    // https://haruple.tistory.com/170 : 넘겨주는 방법

                }

            }
        }

        findID_PW_button.setOnClickListener {
            val intent = Intent(this, FindPwActivity::class.java)
            startActivity(intent)
        }
    }

    fun findID(name:String, phone:String) : Boolean {

        return true
    }
}