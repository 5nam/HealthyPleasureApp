package com.example.healthypleasureapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Calendar_listActivity : AppCompatActivity() {
    lateinit var btnAdd: Button
    lateinit var btnHome: Button
    lateinit var layout: LinearLayout
    lateinit var dateTextView: TextView
    lateinit var imgLeft: ImageView
    lateinit var imgRight: ImageView

    lateinit var dBinfo: DBinfo
    lateinit var sqlitedb: SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_list)

        btnAdd = findViewById(R.id.btnAdd)
        btnHome = findViewById(R.id.btnHome)
        dateTextView = findViewById(R.id.dateTextView)
        imgLeft = findViewById(R.id.imgLeft)
        imgRight = findViewById(R.id.imgRight)

        var year = intent.getIntExtra("N_year",0)
        var month = intent.getIntExtra("N_month",0)
        var day = intent.getIntExtra("N_day",0)
        var userID = "swu"

        //달력의 메인으로 이동
        btnHome.setOnClickListener {
            val intent = Intent(this, Calendar_mainActivity::class.java)
            startActivity(intent)
        }

        //운동내용을 새로 추가
        btnAdd.setOnClickListener {
            val intent = Intent(this, Calendar_infoActivity::class.java)
            intent.putExtra("N_year", year)
            intent.putExtra("N_month", month)
            intent.putExtra("N_day", day)
            startActivity(intent)
        }

        //왼쪽 버튼을 클릭했을 때
        imgLeft.setOnClickListener {
            layout.removeAllViews()

            if (day==1 && month==1){
                day = 31
                month = 12
                year -= 1
            }
            else if (day==1 && (month==2 || month==4 || month==6 || month==9 || month==11)){
                day = 31
                month -= 1
            }
            else if (day==1 && (month==3 || month==5 || month==7 || month==8 || month==10 || month==12)){
                day = 30
                month -=1
            }
            else{
                day -= 1
            }

            dateTextView.text = year.toString() + "년 " + month.toString() +"월 " + day.toString() +"일 "
            dateTextView.textSize = 30f

            dBinfo = DBinfo(this, "Info", null, 1)
            sqlitedb = dBinfo.readableDatabase

            layout = findViewById(R.id.layout)

            var cursor: Cursor
            cursor = sqlitedb.rawQuery("SELECT infoNum, hour, min, memo, kg, cm FROM Info WHERE userID='"+userID+"' AND year="+year+" AND month="+month+" AND day="+day+"", null)

            var num: Int = 0

            while (cursor.moveToNext()) {
                var c_infoNum = cursor.getInt(cursor.getColumnIndex("infoNum"))
                var c_hour = cursor.getInt(cursor.getColumnIndex("hour")).toString()
                var c_min = cursor.getInt(cursor.getColumnIndex("min")).toString()
                var c_memo = cursor.getString(cursor.getColumnIndex("memo"))
                var c_kg = cursor.getInt(cursor.getColumnIndex("kg")).toString()
                var c_cm = cursor.getInt(cursor.getColumnIndex("cm")).toString()
                var count = cursor.count

                var layout_item: LinearLayout = LinearLayout(this)
                layout_item.orientation = LinearLayout.VERTICAL
                layout_item.id = num
                layout_item.background = getDrawable(R.drawable.round_border)


                var tvHourMin: TextView = TextView(this)
                tvHourMin.text = "\n\t\t\t운동시간: " + c_hour.toString() + "시간 " + c_min.toString() + "분"
                layout_item.addView(tvHourMin)

                var tvMemo: TextView = TextView(this)
                tvMemo.text = "\t\t\t운동내용: " + c_memo
                layout_item.addView(tvMemo)

                var tvKg: TextView = TextView(this)
                tvKg.text = "\t\t\t몸무게: " + c_kg.toString() + "kg"
                layout_item.addView(tvKg)

                var tvCm: TextView = TextView(this)
                tvCm.text = "\t\t\t키: " + c_cm.toString() + "cm\n"
                layout_item.addView(tvCm)

                layout_item.setOnClickListener {
                    val intent = Intent(this, Calendar_infoActivity::class.java)
                    intent.putExtra("c_infoNum", c_infoNum)
                    intent.putExtra("c_userID", userID)
                    intent.putExtra("info2",true)
                    startActivity(intent)
                }
                layout.addView(layout_item)
                num++;
            }
            if (num==0){
                var layout_item: LinearLayout = LinearLayout(this)
                layout_item.orientation = LinearLayout.VERTICAL
                layout_item.id = num

                var noList: TextView = TextView(this)
                noList.text = "\n\n\n\n\n\n\n\n운동내용이 없습니다"
                noList.textSize = 20f
                noList.gravity = Gravity.CENTER
                layout_item.addView(noList)
                layout.addView(layout_item)
            }
            cursor.close()
            sqlitedb.close()
            dBinfo.close()
        }

        //오른쪽 버튼을 클릭했을 때
        imgRight.setOnClickListener {
            layout.removeAllViews()
            if (day==12 && month==31){
                day = 1
                month = 1
                year += 1
            }
            else if (day==30 && (month==2 || month==4 || month==6 || month==9 || month==11)){
                day = 1
                month += 1
            }
            else if (day==31 && (month==3 || month==5 || month==7 || month==8 || month==10 || month==12)){
                day = 1
                month +=1
            }
            else{
                day += 1
            }

            dateTextView.text = year.toString() + "년 " + month.toString() +"월 " + day.toString() +"일 "
            dateTextView.textSize = 30f

            dBinfo = DBinfo(this, "Info", null, 1)
            sqlitedb = dBinfo.readableDatabase

            layout = findViewById(R.id.layout)

            var cursor: Cursor
            cursor = sqlitedb.rawQuery("SELECT infoNum, hour, min, memo, kg, cm FROM Info WHERE userID='"+userID+"' AND year="+year+" AND month="+month+" AND day="+day+"", null)

            var num: Int = 0

            while (cursor.moveToNext()) {
                var c_infoNum = cursor.getInt(cursor.getColumnIndex("infoNum"))
                var c_hour = cursor.getInt(cursor.getColumnIndex("hour")).toString()
                var c_min = cursor.getInt(cursor.getColumnIndex("min")).toString()
                var c_memo = cursor.getString(cursor.getColumnIndex("memo"))
                var c_kg = cursor.getInt(cursor.getColumnIndex("kg")).toString()
                var c_cm = cursor.getInt(cursor.getColumnIndex("cm")).toString()
                var count = cursor.count

                var layout_item: LinearLayout = LinearLayout(this)
                layout_item.orientation = LinearLayout.VERTICAL
                layout_item.id = num
                layout_item.background = getDrawable(R.drawable.round_border)

                var tvHourMin: TextView = TextView(this)
                tvHourMin.text = "\n\t\t\t운동시간: " + c_hour.toString() + "시간 " + c_min.toString() + "분"
                layout_item.addView(tvHourMin)

                var tvMemo: TextView = TextView(this)
                tvMemo.text = "\t\t\t운동내용: " + c_memo
                layout_item.addView(tvMemo)

                var tvKg: TextView = TextView(this)
                tvKg.text = "\t\t\t몸무게: " + c_kg.toString() + "kg"
                layout_item.addView(tvKg)

                var tvCm: TextView = TextView(this)
                tvCm.text = "\t\t\t키: " + c_cm.toString() + "cm\n"
                layout_item.addView(tvCm)

                layout_item.setOnClickListener {
                    val intent = Intent(this, Calendar_infoActivity::class.java)
                    intent.putExtra("c_infoNum", c_infoNum)
                    intent.putExtra("c_userID", userID)
                    intent.putExtra("info2",true)
                    startActivity(intent)
                }
                layout.addView(layout_item)
                num++;
            }
            if (num==0){
                var layout_item: LinearLayout = LinearLayout(this)
                layout_item.orientation = LinearLayout.VERTICAL
                layout_item.id = num

                var noList: TextView = TextView(this)
                noList.text = "\n\n\n\n\n\n\n\n운동내용이 없습니다"
                noList.textSize = 20f
                noList.gravity = Gravity.CENTER
                layout_item.addView(noList)
                layout.addView(layout_item)
            }
            cursor.close()
            sqlitedb.close()
            dBinfo.close()
        }

        //달력에서 날짜를 클릭했을 때 데이터 불러오기
        dateTextView.text = year.toString() + "년 " + month.toString() +"월 " + day.toString() +"일 "
        dateTextView.textSize = 30f

        dBinfo = DBinfo(this, "Info", null, 1)
        sqlitedb = dBinfo.readableDatabase

        layout = findViewById(R.id.layout)

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT infoNum, hour, min, memo, kg, cm FROM Info WHERE userID='"+userID+"' AND year="+year+" AND month="+month+" AND day="+day+"", null)

        var num: Int = 0

        while (cursor.moveToNext()) {
            var c_infoNum = cursor.getInt(cursor.getColumnIndex("infoNum"))
            var c_hour = cursor.getInt(cursor.getColumnIndex("hour")).toString()
            var c_min = cursor.getInt(cursor.getColumnIndex("min")).toString()
            var c_memo = cursor.getString(cursor.getColumnIndex("memo"))
            var c_kg = cursor.getInt(cursor.getColumnIndex("kg")).toString()
            var c_cm = cursor.getInt(cursor.getColumnIndex("cm")).toString()
            var count = cursor.count

            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num
            layout_item.background = getDrawable(R.drawable.round_border)

            var tvHourMin: TextView = TextView(this)
            tvHourMin.text = "\n\t\t\t운동시간: " + c_hour.toString() + "시간 " + c_min.toString() + "분"
            layout_item.addView(tvHourMin)

            var tvMemo: TextView = TextView(this)
            tvMemo.text = "\t\t\t운동내용: " + c_memo
            layout_item.addView(tvMemo)

            var tvKg: TextView = TextView(this)
            tvKg.text = "\t\t\t몸무게: " + c_kg.toString() + "kg"
            layout_item.addView(tvKg)

            var tvCm: TextView = TextView(this)
            tvCm.text = "\t\t\t키: " + c_cm.toString() + "cm\n"
            layout_item.addView(tvCm)

            layout_item.setOnClickListener {
                val intent = Intent(this, Calendar_infoActivity::class.java)
                intent.putExtra("c_infoNum", c_infoNum)
                intent.putExtra("c_userID", userID)
                intent.putExtra("info2",true)
                startActivity(intent)
            }
            layout.addView(layout_item)
            num++;
        }
        if (num==0){
            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num

            var noList: TextView = TextView(this)
            noList.text = "\n\n\n\n\n\n\n\n운동내용이 없습니다"
            noList.textSize = 20f
            noList.gravity = Gravity.CENTER
            layout_item.addView(noList)
            layout.addView(layout_item)
        }
        cursor.close()
        sqlitedb.close()
        dBinfo.close()
    }
}
