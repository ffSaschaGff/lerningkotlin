package com.example.saschag.lerningkotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_REQUEST)
        initData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DROP_REQUEST)
        onCreate(db)
    }

    private fun initData(db : SQLiteDatabase) {
        val list = getInitArticleList()
        for (value in list) {
            db.insert(DBTables.Articles.TABLE_NAME, null, value)
        }
    }

    fun getCursorArticleByParent(parent : Long) : Cursor? {
        val db = readableDatabase
        try {
            return db.rawQuery("select * from " + DBTables.Articles.TABLE_NAME + " as T1 where T1."+DBTables.Articles.COLUMN_PARENT+" = "+parent+" order by " + DBTables.Articles.COLUMN_SORT, null)
        } catch (e : SQLiteException) {
            e.printStackTrace()
            return null
        }
    }

    fun getArticleById(id : Long) : Cursor? {
        val db = readableDatabase
        try {
            return db.rawQuery("select * from " + DBTables.Articles.TABLE_NAME + " as T1 where T1."+DBTables.Articles.COLUMN_ID+" = "+id, null)
        } catch (e : SQLiteException) {
            e.printStackTrace()
            return null
        }
    }

    fun getArticleIsArticle(id: Long) : Boolean {
        val db = readableDatabase
        try {
            val cursor = db.rawQuery("select T1."+DBTables.Articles.COLUMN_IS_ARTICLE+" from " + DBTables.Articles.TABLE_NAME + " as T1 where T1."+DBTables.Articles.COLUMN_ID+" = "+id, null)
            if (cursor.moveToFirst()) {
                return cursor.getInt(cursor.getColumnIndex(DBTables.Articles.COLUMN_IS_ARTICLE)) == 1
            }
        } catch (e : SQLiteException) {
            e.printStackTrace()
        }
        return false
    }

    private fun getInitArticleList() : ArrayList<ContentValues> {
        val list = ArrayList<ContentValues>()

        var values = ContentValues()
        values.put(DBTables.Articles.COLUMN_ID, 1)
        values.put(DBTables.Articles.COLUMN_PARENT, 0)
        values.put(DBTables.Articles.COLUMN_TITLE,"Раздел 1")
        values.put(DBTables.Articles.COLUMN_SORT,1)
        values.put(DBTables.Articles.COLUMN_IS_ARTICLE, 0)
        values.put(DBTables.Articles.COLUMN_CONTENT, "")
        list.add(values)

        values = ContentValues()
        values.put(DBTables.Articles.COLUMN_ID, 2)
        values.put(DBTables.Articles.COLUMN_PARENT, 1)
        values.put(DBTables.Articles.COLUMN_TITLE,"Статья 1")
        values.put(DBTables.Articles.COLUMN_SORT,1)
        values.put(DBTables.Articles.COLUMN_IS_ARTICLE, 1)
        values.put(DBTables.Articles.COLUMN_CONTENT, "")

        list.add(values)
        values = ContentValues()
        values.put(DBTables.Articles.COLUMN_ID, 3)
        values.put(DBTables.Articles.COLUMN_PARENT, 4)
        values.put(DBTables.Articles.COLUMN_TITLE,"Статья 2")
        values.put(DBTables.Articles.COLUMN_SORT,1)
        values.put(DBTables.Articles.COLUMN_IS_ARTICLE, 1)
        values.put(DBTables.Articles.COLUMN_CONTENT, "")
        list.add(values)

        values = ContentValues()
        values.put(DBTables.Articles.COLUMN_ID, 4)
        values.put(DBTables.Articles.COLUMN_PARENT, 0)
        values.put(DBTables.Articles.COLUMN_TITLE,"Раздел 2")
        values.put(DBTables.Articles.COLUMN_SORT,1)
        values.put(DBTables.Articles.COLUMN_IS_ARTICLE, 0)
        values.put(DBTables.Articles.COLUMN_CONTENT, "")
        list.add(values)

        return list
    }

    companion object {

        private val DATABASE_NAME = "saschag.help_keeper.db"
        private val DATABASE_VERSION = 3
        private val SQL_CREATE_REQUEST = "create table " + DBTables.Articles.TABLE_NAME + " (" +
                DBTables.Articles.COLUMN_ID + " integer primary key," +
                DBTables.Articles.COLUMN_TITLE + " text," +
                DBTables.Articles.COLUMN_CONTENT + " text," +
                DBTables.Articles.COLUMN_PARENT + " integer," +
                DBTables.Articles.COLUMN_IS_ARTICLE + " integer," +
                DBTables.Articles.COLUMN_SORT + " integer)"
        private val SQL_DROP_REQUEST = "drop table if exists " + DBTables.Articles.TABLE_NAME
    }

}