package com.example.healthypleasureapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FindPwActivity : AppCompatActivity() {

    lateinit var dbManager: DBHelper
    lateinit var sqlitedb: SQLiteDatabase

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

        dbManager = DBHelper(this, "login.db", null, 1)
        sqlitedb = dbManager.readableDatabase

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
                // 데이터베이스에 해당 이름과 비밀번호가 있는지와 알맞은지 체크
                var check = findPW(findPW_ID_Edit.text.toString(), findPW_Phone_Edit.text.toString())
                if(check.equals("not found")) {
                    Toast.makeText(this,"정보를 찾을 수 없습니다. \n 올바른 정보를 입력해주세요.", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, FindPwActivity::class.java)
                    startActivity(intent)
                }
                else {
                    // 있다면, 그 키로 아이디 찾아서 화면에 띄어줌 : 근데, 여기서 띄우는 방법은 없을까?
                    // 왜냐하면 FindIDresultActivity에서 띄우면 한번 더 탐색하고 찾아야 하므로 아예 값을 넘겨주는 방법은 없나?
                    // https://haruple.tistory.com/170 : 넘겨주는 방법
                    val intent = Intent(this, FindPWresultActivity::class.java)
                    intent.putExtra("foundPW", check)
                    startActivity(intent)
                }
            }
        }

        findPW_login_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Range")
    fun findPW(id:String, phone:String) : String {
        var checkID: String
        var checkPhone: String
        var foundPW: String
        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM users WHERE username = '" + id +"';", null)

        if(cursor.moveToNext()) {
            checkID = cursor.getString(cursor.getColumnIndex("username")).toString()
            checkPhone = cursor.getString(cursor.getColumnIndex("phonenumber")).toString()
            foundPW = cursor.getString(cursor.getColumnIndex("password")).toString()

            cursor.close()
            sqlitedb.close()
            dbManager.close()

            if (checkID.equals(id) && checkPhone.equals(phone)) {
                return foundPW
            }
            else {
                return "not found"
            }
        }
        return ""
    }
}