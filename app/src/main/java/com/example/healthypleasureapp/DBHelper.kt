package com.example.healthypleasureapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DBHelper(context: Context,
                name: String?,
                factory: SQLiteDatabase.CursorFactory?,
                version: Int) : SQLiteOpenHelper(context, name, factory, version) {
    
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT, realname TEXT, phonenumber TEXT, birthday TEXT)")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("drop table if exists users")
    }

    fun insertData(username: String, password: String, realname: String, phonenumber: String, birthday: String): Boolean {
        val sqLiteDatabase = this.writableDatabase

        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        values.put("realname", realname)
        values.put("phonenumber", phonenumber)
        values.put("birthday", birthday)
        val result = sqLiteDatabase.insert("users", null, values)
        return if (result == -1L) false else true
    }

    fun checkusername(username: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor =
            sqLiteDatabase.rawQuery("select*from users where username=?", arrayOf(username))
        return if (cursor.count > 0) true else false
    }

    fun checkusernamepassword(username: String, password: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "select*from users where username=? and password=?",
            arrayOf(username, password)
        )
        return if (cursor.count > 0) true else false
    }

    companion object {
        const val DBName = "login.db"
    }
}