package com.example.sevenminuteworkout

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.annotation.ContentView
import java.util.*
import kotlin.collections.ArrayList

class SqliteOpenHelper(context:Context,factory:SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME,factory, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "SevenMinuteWprkoutApp"
        private val TABLE_HISTORY = "history"
        private val COLUMN_ID = "_id"
        private val COLUMN_COMPLETE_DATE = "completed_data"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CreateExerciseTable=("CREATE TABLE "+TABLE_HISTORY+"("+COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_COMPLETE_DATE+" TEXT)")
        p0?.execSQL(CreateExerciseTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS"+ TABLE_HISTORY)
        onCreate(p0)
    }
    fun addDate(date:String){
        val values=ContentValues()
        values.put(COLUMN_COMPLETE_DATE,date)
        val db=this.writableDatabase
        db.insert(TABLE_HISTORY,null,values)
        db.close()

    }

    @SuppressLint("Range")
    fun getAllCompletedDateList():ArrayList<String>{
        val list=ArrayList<String>()
        val db=this.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM $TABLE_HISTORY",null)
        while(cursor.moveToNext()){
            val dateValue=(cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETE_DATE)))
            list.add(dateValue)
        }
        cursor.close()
        return list
    }
}