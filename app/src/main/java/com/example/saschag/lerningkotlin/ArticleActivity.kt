package com.example.saschag.lerningkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val id = intent.getIntExtra("article_id", 0)
        val cursor = DBHelper(applicationContext).getArticleById(id)
        if (cursor != null && cursor.moveToFirst()) {
            findViewById<TextView>(R.id.textViewTitle).text = cursor.getString(cursor.getColumnIndex(DBTables.Articles.COLUMN_TITLE))
            findViewById<TextView>(R.id.textViewContent).text = cursor.getString(cursor.getColumnIndex(DBTables.Articles.COLUMN_CONTENT))
        }

    }

}