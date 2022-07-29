package com.example.healthypleasureapp

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.os.Bundle as Bundle

class CalcActivity : AppCompatActivity() {
    lateinit var calc_Button: Button
    lateinit var heightEditText: EditText
    lateinit var weightEditText: EditText
    lateinit var ageEditText: EditText

    lateinit var resultTextView: TextView
    lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        calc_Button = findViewById<Button>(R.id.calc_Button)
        heightEditText = findViewById<EditText>(R.id.heightEditText)
        weightEditText = findViewById<EditText>(R.id.weightEditText)
        ageEditText = findViewById<EditText>(R.id.ageEditText)

        //loadData()

        calc_Button.setOnClickListener {
            /* ageEditText.length() <= 0 || 나이는 필수 입력 X?*/
            if ( ageEditText.length() <= 0 || heightEditText.length() <= 0 || weightEditText.length() <= 0) {
                Toast.makeText(this, "키, 몸무게를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                // 계산 버튼 클릭 시 마지막에 입력한 내용 저장
                saveData(
                    ageEditText.text.toString().toInt(), heightEditText.text.toString().toInt(),
                    weightEditText.text.toString().toInt()
                )
                ResultData(
                    ageEditText.text.toString().toInt(),
                    heightEditText.text.toString().toInt(),
                    weightEditText.text.toString().toInt())
                //var intent = Intent(this, ResultActivity::class.java)


                intent.putExtra("age", ageEditText.text.toString())
                intent.putExtra("height", heightEditText.text.toString())
                intent.putExtra("weight", weightEditText.text.toString())

                //startActivity(intent)
            }
        }

    }

    //데이터 저장
    private fun saveData(age: Int, height: Int, weight: Int){


        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putInt("KEY_AGE", ageEditText.text.toString().toInt()).apply()
        editor.putInt("KEY_HEIGHT", heightEditText.text.toString().toInt()).apply()
        editor.putInt("KEY_WEIGHT", weightEditText.text.toString().toInt()).apply()
    }

    private fun loadData(age: Int, height: Int, weight: Int){
        var pref = this.getPreferences(0)
        var age = pref.getInt("KEY_AGE",0)
        var height = pref.getInt("KEY_HEIGHT",0)
        var weight = pref.getInt("KEY_WEIGHT", 0)

        if(age!=0 && height!=0 && weight!=0){
            ageEditText.setText(age.toString())
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }


    private fun ResultData(age: Int, height: Int, weight: Int) {
        resultTextView = findViewById<TextView>(R.id.resultTextView)
        seekBar = findViewById<SeekBar>(R.id.seekBar)
        // var age = intent.getStringExtra("age")!!.toInt()
        // var height = intent.getStringExtra("height")!!.toInt()
        // var weight = intent.getStringExtra("weight")!!.toInt()

        //BMI 계산기
        var bmi = weight / Math.pow(height/100.0, 2.0)
        bmi = Math.round(bmi*100/100.0).toDouble()
        //글자 출력
        when{
            bmi >= 35 -> resultTextView.text = bmi.toString()+" ( 고도 비만 )"
            bmi >= 25 -> resultTextView.text = bmi.toString()+" ( 비만 )"
            bmi >= 23 -> resultTextView.text = bmi.toString()+" ( 과체중 )"
            bmi >= 18.5 -> resultTextView.text = bmi.toString()+" ( 정상 )"
            else -> resultTextView.text = bmi.toString()+" ( 저체중 )"
        }

        when{
            bmi >= 35 -> seekBar.setProgress(4)
            bmi >= 25 -> seekBar.setProgress(3)
            bmi >= 23 -> seekBar.setProgress(2)
            bmi >= 18.5 -> seekBar.setProgress(1)
            else -> seekBar.setProgress(0)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // xml 을 이용한 메뉴 만들기
        return when(item?.itemId) {
            R.id.item_calendar -> {
                val intent = Intent(this, Calendar_mainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.item_stopwatch -> {
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.item_calculator -> {
                val intent = Intent(this, CalcActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.item_logout -> {
                val intent = Intent(this, LogoutActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}