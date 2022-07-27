package com.example.healthypleasureapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.example.healthypleasureapp.DBHelper
import android.os.Bundle
//import com.android.HealthyPleasureApp.R
import android.text.TextUtils
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button
//import com.example.healthypleasureapp.Homeactivity
import com.example.healthypleasureapp.LoginActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var repassword: EditText
    lateinit var realname: EditText
    lateinit var phonenumber: EditText
    lateinit var birthday: EditText
    lateinit var signup: Button
    lateinit var signin: Button
    lateinit var DB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        signup = findViewById(R.id.signup)
        signin = findViewById(R.id.signin)
        realname = findViewById(R.id.realname)
        phonenumber = findViewById(R.id.phonenumber)
        birthday = findViewById(R.id.birthday)
        DB = DBHelper(this, "login.db", null, 1)


        signup.setOnClickListener(View.OnClickListener {
            val user = username.getText().toString()
            val pass = password.getText().toString()
            val repass = repassword.getText().toString()
            val name = realname.getText().toString()
            val phone = phonenumber.getText().toString()
            val birth = birthday.getText().toString()

            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)
                || TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(birth)
            ) Toast.makeText(this@RegisterActivity, "모든 항목 입력 필수입니다", Toast.LENGTH_SHORT).show() else {
                if (pass == repass) {
                    val checkuser = DB.checkusername(user)
                    if (checkuser == false) {
                        val insert = DB.insertData(user, pass, name, phone, birth)
                        if (insert == true) {
                            Toast.makeText(this@RegisterActivity, "등록되었습니다", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, RegisterActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@RegisterActivity, "등록에 실패하였습니다", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(this@RegisterActivity, "존재하는 사용자입니다", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                }
            }
        })
        signin.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        })
    }
}