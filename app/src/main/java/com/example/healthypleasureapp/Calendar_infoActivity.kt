package com.example.healthypleasureapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Calendar_infoActivity : AppCompatActivity() {
    lateinit var edtHour: EditText
    lateinit var edtMin: EditText
    lateinit var edtMemo: EditText
    lateinit var edtKg: EditText
    lateinit var edtCm: EditText
    lateinit var btnSave: Button
    lateinit var btnCancel: Button
    lateinit var btnUpdate: Button
    lateinit var btnList: Button
    lateinit var btnDelete: Button

    lateinit var dbinfo: DBinfo
    lateinit var sqlitedb: SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_info)
        edtHour = findViewById(R.id.edtHour)
        edtMin = findViewById(R.id.edtMin)
        edtMemo = findViewById(R.id.edtMemo)
        edtKg = findViewById(R.id.edtKg)
        edtCm = findViewById(R.id.edtCm)

        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnList = findViewById(R.id.btnList)
        btnDelete = findViewById(R.id.btnDelete)

        var year = intent.getIntExtra("N_year",0)
        var month = intent.getIntExtra("N_month",0)
        var day = intent.getIntExtra("N_day",0)
        val value1 = intent.getIntExtra("c_infoNum",0)
        val value2 = intent.getStringExtra("c_userID")

        var info2 = false
        info2 = intent.getBooleanExtra("info2",false)

        //항목 클릭시 수정 및 삭제 버튼, 추가 클릭시 저장 및 취소버튼
        if (info2==true){
            btnSave.visibility = View.GONE
            btnCancel.visibility = View.GONE
            btnUpdate.visibility = View.VISIBLE
            btnList.visibility = View.VISIBLE
            btnDelete.visibility = View.VISIBLE

            var cursor: Cursor

            dbinfo = DBinfo(this, "Info", null, 1)
            sqlitedb = dbinfo.readableDatabase
            cursor = sqlitedb.rawQuery("SELECT hour, min, memo, kg, cm FROM Info WHERE infoNum="+value1+" AND userID='"+value2+"'", null)

            while (cursor.moveToNext()) {
                var c_hour = cursor.getInt(cursor.getColumnIndex("hour")).toString()
                var c_min = cursor.getInt(cursor.getColumnIndex("min")).toString()
                var c_memo = cursor.getString(cursor.getColumnIndex("memo"))
                var c_kg = cursor.getInt(cursor.getColumnIndex("kg")).toString()
                var c_cm = cursor.getInt(cursor.getColumnIndex("cm")).toString()

                edtHour.setText(c_hour)
                edtMin.setText(c_min)
                edtMemo.setText(c_memo)
                edtKg.setText(c_kg)
                edtCm.setText(c_cm)
            }
        }
        else {
            btnSave.visibility = View.VISIBLE
            btnCancel.visibility = View.VISIBLE
            btnUpdate.visibility = View.GONE
            btnList.visibility = View.GONE
            btnDelete.visibility = View.GONE
        }


        //취소버튼 클릭 시 운동내용으로 이동
        btnCancel.setOnClickListener {
            Toast.makeText(applicationContext, "취소되었습니다", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Calendar_listActivity::class.java)
            intent.putExtra("N_year", year)
            intent.putExtra("N_month", month)
            intent.putExtra("N_day", day)
            startActivity(intent)
        }

        //저장버튼 클릭 시 운동내용 저장
        btnSave.setOnClickListener {
            dbinfo = DBinfo(this, "Info", null, 1)

            var userID : String = "swu"
            var c_hour: String = edtHour.text.toString()
            var c_min: String = edtMin.text.toString()
            var c_memo: String = edtMemo.text.toString()
            var c_kg: String = edtKg.text.toString()
            var c_cm: String = edtCm.text.toString()

            //운동내용 중 한 곳이라도 빈칸이 있으면 '잘못된 입력입니다'라는 문구 출력
            if(c_hour==""){
                Toast.makeText(applicationContext, "시간을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_min==""){
                Toast.makeText(applicationContext, "분을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_memo==""){
                Toast.makeText(applicationContext, "운동내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_kg==""){
                Toast.makeText(applicationContext, "몸무게를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_cm==""){
                Toast.makeText(applicationContext, "키를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_hour.toInt()>24 || c_hour.toInt()<0) {
                Toast.makeText(applicationContext, "시간은 0~24 범위안에서만 입력가능합니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_min.toInt()>59 || c_min.toInt()<0) {
                Toast.makeText(applicationContext, "분은 0~59 범위안에서만 입력가능합니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_hour.toInt()==24 && c_min.toInt()>0) {
                Toast.makeText(applicationContext, "잘못된 시간입니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_hour.toInt()>24 || c_hour.toInt()<0) {
                Toast.makeText(applicationContext, "시간은 0~24 범위안에서만 입력가능합니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_kg.toInt()>1000 || c_kg.toInt()<15) {
                Toast.makeText(applicationContext, "입력하신 $c_kg kg 은 유효하지 않습니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_cm.toInt()>1000 || c_cm.toInt()<15) {
                Toast.makeText(applicationContext, "입력하신 $c_cm cm 은 유효하지 않습니다", Toast.LENGTH_SHORT).show()
            }
            else { //운동내용을 Info 데이터베이스에 저장

                //다른 내용과 구별할 고유 번호 생성
                var countNum : Int = 0
                var cursor : Cursor

                sqlitedb = dbinfo.readableDatabase
                cursor = sqlitedb.rawQuery("SELECT infoNum FROM Info", null)
                while (cursor.moveToNext()){
                    countNum = cursor.getInt(cursor.getColumnIndex("infoNum"))
                }
                countNum++

                //운동내용 데이터에 저장
                sqlitedb = dbinfo.writableDatabase
                sqlitedb.execSQL(
                    "INSERT INTO Info VALUES (" + countNum + ",'" + userID + "', " + year + ", " + month + ", " + day + ", " +
                            "" + c_hour + "," + c_min + ",'" + c_memo + "'," + c_kg + "," + c_cm + ")"
                )
                sqlitedb.close()
                dbinfo.close()

                Toast.makeText(applicationContext, "저장되었습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Calendar_listActivity::class.java)
                intent.putExtra("N_year", year)
                intent.putExtra("N_month", month)
                intent.putExtra("N_day", day)
                startActivity(intent)
            }
        }

        //운동내용을 수정
        btnUpdate.setOnClickListener {
            dbinfo = DBinfo(this, "Info", null, 1)
            sqlitedb = dbinfo.writableDatabase
            var c_hour: String = edtHour.text.toString()
            var c_min: String = edtMin.text.toString()
            var c_memo: String = edtMemo.text.toString()
            var c_kg: String = edtKg.text.toString()
            var c_cm: String = edtCm.text.toString()

            if(c_hour==""){
                Toast.makeText(applicationContext, "시간을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_min==""){
                Toast.makeText(applicationContext, "분을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_memo==""){
                Toast.makeText(applicationContext, "운동내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_kg==""){
                Toast.makeText(applicationContext, "몸무게를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_cm==""){
                Toast.makeText(applicationContext, "키를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(c_hour.toInt()>24 || c_hour.toInt()<0) {
                Toast.makeText(applicationContext, "시간은 0~24 범위안에서만 입력가능합니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_min.toInt()>59 || c_min.toInt()<0) {
                Toast.makeText(applicationContext, "분은 0~59 범위안에서만 입력가능합니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_hour.toInt()==24 && c_min.toInt()>0) {
                Toast.makeText(applicationContext, "잘못된 시간입니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_hour.toInt()>24 || c_hour.toInt()<0) {
                Toast.makeText(applicationContext, "시간은 0~24 범위안에서만 입력가능합니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_kg.toInt()>1000 || c_kg.toInt()<15) {
                Toast.makeText(applicationContext, "입력하신 $c_kg kg 은 유효하지 않습니다", Toast.LENGTH_SHORT).show()
            }
            else if(c_cm.toInt()>1000 || c_cm.toInt()<15) {
                Toast.makeText(applicationContext, "입력하신 $c_cm cm 은 유효하지 않습니다", Toast.LENGTH_SHORT).show()
            }
            else {
                sqlitedb.execSQL("UPDATE Info SET hour=" + c_hour + " WHERE  infoNum=" + value1 + " AND userID='" + value2 + "'")
                sqlitedb.execSQL("UPDATE Info SET min=" + c_min + " WHERE  infoNum=" + value1 + " AND userID='" + value2 + "'")
                sqlitedb.execSQL("UPDATE Info SET memo='" + c_memo + "' WHERE  infoNum=" + value1 + " AND userID='" + value2 + "'")
                sqlitedb.execSQL("UPDATE Info SET kg=" + c_kg + " WHERE  infoNum=" + value1 + " AND userID='" + value2 + "'")
                sqlitedb.execSQL("UPDATE Info SET cm=" + c_cm + " WHERE  infoNum=" + value1 + " AND userID='" + value2 + "'")

                var cursor: Cursor
                sqlitedb = dbinfo.readableDatabase
                cursor = sqlitedb.rawQuery("SELECT year, month, day FROM Info WHERE infoNum=" + value1 + " AND userID='" + value2 + "'", null)

                var year = 0
                var month = 0
                var day = 0
                while (cursor.moveToNext()){
                    year = cursor.getInt(cursor.getColumnIndex("year"))
                    month = cursor.getInt(cursor.getColumnIndex("month"))
                    day = cursor.getInt(cursor.getColumnIndex("day"))
                }
                dbinfo.close()
                sqlitedb.close()

                Toast.makeText(applicationContext, "수정되었습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Calendar_listActivity::class.java)
                intent.putExtra("N_year", year)
                intent.putExtra("N_month", month)
                intent.putExtra("N_day", day)
                startActivity(intent)
            }
        }

        //해당날짜의 운동내용 목록으로 이동
        btnList.setOnClickListener {
            dbinfo = DBinfo(this, "Info", null, 1)
            var cursor: Cursor
            sqlitedb = dbinfo.readableDatabase
            cursor = sqlitedb.rawQuery("SELECT year, month, day FROM Info WHERE infoNum=" + value1 + " AND userID='" + value2 + "'", null)

            var year = 0
            var month = 0
            var day = 0
            while (cursor.moveToNext()){
                year = cursor.getInt(cursor.getColumnIndex("year"))
                month = cursor.getInt(cursor.getColumnIndex("month"))
                day = cursor.getInt(cursor.getColumnIndex("day"))
            }
            dbinfo.close()
            sqlitedb.close()

            val intent = Intent(this, Calendar_listActivity::class.java)
            intent.putExtra("N_year", year)
            intent.putExtra("N_month", month)
            intent.putExtra("N_day", day)
            startActivity(intent)
        }

        //운동내용을 삭제
        btnDelete.setOnClickListener {
            dbinfo = DBinfo(this, "Info", null, 1)
            var cursor: Cursor
            sqlitedb = dbinfo.readableDatabase
            cursor = sqlitedb.rawQuery("SELECT year, month, day FROM Info WHERE infoNum=" + value1 + " AND userID='" + value2 + "'", null)

            var year = 0
            var month = 0
            var day = 0
            while (cursor.moveToNext()){
                year = cursor.getInt(cursor.getColumnIndex("year"))
                month = cursor.getInt(cursor.getColumnIndex("month"))
                day = cursor.getInt(cursor.getColumnIndex("day"))
            }

            var c_hour: String = edtHour.text.toString()
            var c_min: String = edtMin.text.toString()
            var c_memo: String = edtMemo.text.toString()
            var c_kg: String = edtKg.text.toString()
            var c_cm: String = edtCm.text.toString()

            if(c_hour=="" || c_min=="" || c_memo=="" || c_kg=="" || c_cm==""){
                Toast.makeText(applicationContext, "잘못된 입력입니다", Toast.LENGTH_SHORT).show()
            }
            else {
                sqlitedb = dbinfo.writableDatabase
                sqlitedb.execSQL("DELETE FROM Info WHERE infoNum=" + value1 + " AND userID='" + value2 + "'")
                dbinfo.close()
                sqlitedb.close()

                Toast.makeText(applicationContext, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Calendar_listActivity::class.java)
                intent.putExtra("N_year", year)
                intent.putExtra("N_month", month)
                intent.putExtra("N_day", day)
                startActivity(intent)
            }
        }
    }
}