package com.greedy.hinote

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/* 안드로이드에는 경량형 데이터 베이스 SQLite가 내장되어 있다.
* 데이터베이스를 파일로 생성하고 코틀린 코드에서 사용할 수 있도록 데이터 베이스와 연결하는 역할을 하는
* Helper 클래스가 있다.
* Context, 데이터베이스명, 팩토리, 버전정보가 생성자로 전달되는데 팩토리는 사용하지 않으므로(null) 세 가지 정보만 전달한다. */
class sqliteHelper (context: Context, name:String, version: Int) : SQLiteOpenHelper(context, name, null, version){
    /* 데이터베이스가 생성될 때 동작하는 메소드 */
    override fun onCreate(db: SQLiteDatabase?) {

        /* 데이터베이스가 생성될 때 테이블을 생성한다. */
        /* 내장되어있는 db쪽으로 실행한다.*/
        val create = "create table memo (" +
                "number integer primary key," +
                "content text, " +
                "datetime integer, " +
                "isbn text" +
                ")"

        db?.execSQL(create)
    }

    /* 데이터베이스가 업그레이드 될 때 동작하는 메소드 */
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        /* 내용 없음 */
    }

    /* 1. insert */
    fun insertMemo(memo: Memo) {

        /* 저장할 데이터를  ContentValues에 key value 방식으로 저장한다. */
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)
        values.put("isbn",memo.isbn)
        Log.d( "isbn", memo.isbn!!)
        /* writableDatabase 속성에 테이블명, 작성한 값을 전달하여 insert */
        val wd = writableDatabase
        wd.insert("memo", null, values)
        wd.close()
    }

    /* 2. select */
    @SuppressLint("Range")
    fun selectMemo(isbn: String?): MutableList<Memo> {

        /* 데이터베이스가 존재하지 않는다면 onCreate를 호출해서 테이블을 생성한다. */
        if(readableDatabase == null) {
            onCreate(readableDatabase)
        }

        /* 조회 시에는 readableDatabase를 이용한다. */
        val rd = readableDatabase

        val select = "select * from memo where isbn = ${isbn}"
        val list = mutableListOf<Memo>()

        /* 조회 결과는 cursor 형태로 되돌아 오는데 조회 시쿼리문과 쿼리문에 전달할 값을 인자로 전달한다.*/
        var cursor = rd.rawQuery(select, null)

        while(cursor.moveToNext()) {
            val no = cursor.getLong(cursor.getColumnIndex("number"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))
            val isbn = cursor.getString(cursor.getColumnIndex("isbn"))
            list.add(Memo(no, content, datetime, isbn))
        }

        cursor.close()
        rd.close()

        return list
    }

    /* 3. delete */

    /* (memo: Memo?) 에서 ?를 지워주어야함 */
    fun deleteMemo(memo: Memo) {
        val delete = "delete from memo where number = ${memo.no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }


}