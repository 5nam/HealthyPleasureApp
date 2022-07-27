package com.example.healthypleasureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class LoginActivity : AppCompatActivity() {

    lateinit var loginIdEdt: EditText
    lateinit var loginPwEdt: EditText
    lateinit var loginKeep: CheckBox
    lateinit var logIn: Button
    lateinit var findIdPw: TextView
    lateinit var loginSingUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginIdEdt = findViewById(R.id.ID_Edit)
        loginPwEdt = findViewById(R.id.PW_Edit)
        loginKeep = findViewById(R.id.login_checkBox)
        logIn = findViewById(R.id.login_button)
        findIdPw = findViewById(R.id.find_idpw_TextView)
        loginSingUp = findViewById(R.id.signup_TextView)

        // 로그인 버튼 클릭
        logIn.setOnClickListener {

            // 아이디, 비밀번호란이 비어있지 않은지
            if(loginIdEdt.length() == 0) {
                Toast.makeText(this,"아이디를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
            else if(loginPwEdt.length() == 0) {
                Toast.makeText(this,"비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
            else {
                // 데이터베이스에 해당 ID/PW 가 있는지와 알맞은지 체크 (함수로 구현)
                if(checkIDPW(loginIdEdt.toString(),loginPwEdt.toString())) {
                    if(loginKeep.isChecked){ // 로그인 유지 체크 여부 판단
                        // 유지 기능 함수

                    }
                    // 달력 화면으로 가기
                }
                else if(checkIDPW(loginIdEdt.toString(),loginPwEdt.toString())){
                    // 로그인 정보가 맞지 않는다는 메시지 출력
                    Toast.makeText(this,"로그인 정보가 맞지 않습니다.\n다시 입력해주세요.",Toast.LENGTH_LONG).show()
                }
            }
        }

        findIdPw.setOnClickListener {
            val intent = Intent(this, FindActivity::class.java)
            startActivity(intent)
        }

        loginSingUp.setOnClickListener {
            // 회원가입창이 완성되면 그때 intent 로 화면 전환

        }
    }

    // 데이터베이스에 해당 ID/PW 가 있는지 찾고 알맞은지 확인하는 함수 : 리턴값 Boolean
    fun checkIDPW(ID: String, PW: String): Boolean {
        return true
    }

    // 로그인 유지 기능 구현하는 함수 : https://jangstory.tistory.com/7?category=874426

    // SNS(구글만) 로그인 기능 함수 : https://in0407.tistory.com/2?category=999175
}