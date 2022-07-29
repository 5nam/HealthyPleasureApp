package com.example.healthypleasureapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class LoginActivity : AppCompatActivity() {
    lateinit var dbManager: DBHelper
    lateinit var sqlitedb: SQLiteDatabase

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

        dbManager = DBHelper(this, "login.db", null, 1)
        sqlitedb = dbManager.readableDatabase

        if(checkAutoLogin()) { // 자동로그인일때
            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Calendar_mainActivity::class.java)
            startActivity(intent)
        }

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
                if(checkIDPW(loginIdEdt.text.toString(),loginPwEdt.text.toString())) {
                    if(loginKeep.isChecked){ // 로그인 유지 체크 여부 판단
                        // 유지 기능 함수
                        saveLogin(loginIdEdt.text.toString(), loginPwEdt.text.toString())
                        Toast.makeText(this,"로그인 정보를 유지합니다.",Toast.LENGTH_LONG).show()
                        Toast.makeText(this,"로그인에 성공하였습니다.",Toast.LENGTH_LONG).show()
                    }
                    Toast.makeText(this,"로그인에 성공하였습니다.",Toast.LENGTH_LONG).show()
                    // 달력 화면으로 가기
                    val intent = Intent(this, Calendar_mainActivity::class.java)
                    intent.putExtra("N_userID", loginIdEdt.text.toString())
                    startActivity(intent)
                }
                else {
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
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // 데이터베이스에 해당 ID/PW 가 있는지 찾고 알맞은지 확인하는 함수 : 리턴값 Boolean
    @SuppressLint("Range")
    fun checkIDPW(ID: String, PW: String): Boolean {
        var checkID: String
        var checkPW: String
        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM users WHERE username = '" + ID +"';", null)

        if(cursor.moveToNext()) {
            checkID = cursor.getString(cursor.getColumnIndex("username")).toString()
            checkPW = cursor.getString(cursor.getColumnIndex("password")).toString()

            cursor.close()
            sqlitedb.close()
            dbManager.close()

            return checkID == ID && checkPW == PW
        }
        return false
    }

    // 로그인 유지 기능 구현하는 함수 : https://jangstory.tistory.com/7?category=874426
    // 원래 캐시를 통해서 자동로그인을 구현해야 하는데, 그럼 서버연결 등을 해야한다고 해서 MyShared 방식으로 데이터 저장으로 구현!
    // 자동 로그인 정보 저장
    fun saveLogin(ID: String, PW: String) {
        MySharedPreferences.setUserId(this, ID)
        MySharedPreferences.setUserPass(this, PW)
    }

    // 자동 로그인 정보 존재하는지 체크
    fun checkAutoLogin():Boolean {
        // SharedPreferences 안에 값이 없을때, 있을때
        return !(MySharedPreferences.getUserId(this).isNullOrBlank()
                || MySharedPreferences.getUserPass(this).isNullOrBlank())
    }

}