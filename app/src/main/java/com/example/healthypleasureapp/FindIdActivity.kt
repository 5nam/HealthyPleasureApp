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

class FindIdActivity : AppCompatActivity() {

    lateinit var dbManager: DBHelper
    lateinit var sqlitedb: SQLiteDatabase
    
    lateinit var findID_name_Edit: EditText
    lateinit var findID_Phone_Edit: EditText
    lateinit var findID_ID_button: Button
    lateinit var findID_PW_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id)

        findID_name_Edit = findViewById<EditText>(R.id.findID_name_Edit)
        findID_Phone_Edit = findViewById<EditText>(R.id.findID_Phone_Edit)
        findID_ID_button = findViewById(R.id.findID_ID_button)
        findID_PW_button = findViewById(R.id.findID_PW_button)

        dbManager = DBHelper(this, "login.db", null, 1)
        sqlitedb = dbManager.readableDatabase

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
                var check = findID(findID_name_Edit.text.toString(), findID_Phone_Edit.text.toString())
                // 해당 계정을 찾을 수 없을 경우
                if(check.equals("not found")) {
                    Toast.makeText(this,"정보를 찾을 수 없습니다.\n올바른 정보를 입력해주세요.", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, FindIdActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    // 있다면, 그 키로 아이디 찾아서 FindIDresultActivity 로 데이터 넘겨줌
                    val intent = Intent(this, FindIDresultActivity::class.java)
                    intent.putExtra("foundID", check)
                    startActivity(intent)
                    finish()
                }
            }
        }

        findID_PW_button.setOnClickListener {
            val intent = Intent(this, FindPwActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // name, phone 정보로 찾는 것!
    // 찾으면 그 아이디 반환, 못 찾으면 not found 반환
    @SuppressLint("Range")
    fun findID(name:String, phone:String) : String {
        var checkName: String
        var checkPhone: String
        var foundID: String
        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM users WHERE phonenumber = '" + phone +"';", null)

        if(cursor.moveToNext()) {
            checkName = cursor.getString(cursor.getColumnIndex("realname")).toString()
            checkPhone = cursor.getString(cursor.getColumnIndex("phonenumber")).toString()
            foundID = cursor.getString(cursor.getColumnIndex("username")).toString()

            cursor.close()
            sqlitedb.close()
            dbManager.close()

            if (checkName.equals(name) && checkPhone.equals(phone)) {
                return foundID
            }
            else {
                return "not found"
            }
        }
        return "not found"
    }
}