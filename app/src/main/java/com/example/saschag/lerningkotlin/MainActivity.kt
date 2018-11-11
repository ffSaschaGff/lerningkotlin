package com.example.saschag.lerningkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = intent.getIntExtra("parent_id", 0)
        val dbHelper = DBHelper(applicationContext)
        val cursor = dbHelper.getCursorArticleByParent(id)
        val adapter = MainListAdapter(applicationContext,R.layout.list_item,cursor, arrayOf(DBTables.Articles.COLUMN_TITLE), intArrayOf(android.R.id.text1),0,id)
        val list = findViewById<ListView>(R.id.main_list)

        list.adapter = adapter

        //list.setOnItemClickListener(MainListClickLisner(this))
    }

    /*class MainListClickLisner(private val activity: MainActivity) : AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, pos: Int, id: Long) {
            if (DBHelper(activity).getArticleIsArticle(id)) {
                val intent = Intent(activity, ArticleActivity::class.java)
                intent.putExtra("article_id", id)
                activity.startActivity(intent)
            } else {
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("parent_id", id)
                activity.startActivity(intent)
            }
        }

    }*/
}
