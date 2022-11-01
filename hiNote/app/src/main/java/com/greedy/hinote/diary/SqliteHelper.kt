package com.greedy.hinote.diary

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {

        val create = "create table diary(" +
                "no integer primary key," +
                "diaryContent text, " +
                "diaryDate text" +
                ")"

        db?.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

       //수정
       fun onUpgrade(diary:Diary) {
        val wd = writableDatabase
        val values = ContentValues()
        values.put("diaryContent", diary.diaryContent)
        values.put("diaryDate", diary.diaryDate)

        wd.update("diary", values, "no = ${diary.no}", null)
        wd.close()
    }

    fun insertDiary(diary: Diary){

        //내용저장
        val values = ContentValues()
        values.put("diaryContent", diary.diaryContent)
        values.put("diaryDate", diary.diaryDate)

        val wd = writableDatabase
        wd.insert("diary", null, values)
        wd.close()
    }

    @SuppressLint("Range")
    fun selectDiary(): MutableList<Diary>{

        if(readableDatabase == null){
            onCreate(readableDatabase)
        }

        val rd = readableDatabase
        val select = "select * from diary"
        val diaryList = mutableListOf<Diary>()

        var cursor = rd.rawQuery(select, null)

        while(cursor.moveToNext()){
            val no = cursor.getLong(cursor.getColumnIndex("no"))
            val diaryContent = cursor.getString(cursor.getColumnIndex("diaryContent"))
            val diaryDate = cursor.getString(cursor.getColumnIndex("diaryDate"))
            diaryList.add(Diary(no, diaryContent, diaryDate))
        }

        cursor.close()
        rd.close()

        return diaryList

    }

    fun deleteDiary(diary: Diary){
        val delete = "delete from diary where no = ${diary.no}"
        val wd = writableDatabase
        wd.execSQL(delete)
        wd.close()
    }


}