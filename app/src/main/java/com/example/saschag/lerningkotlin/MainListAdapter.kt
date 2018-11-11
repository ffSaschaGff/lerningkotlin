package com.example.saschag.lerningkotlin

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SimpleCursorAdapter

class MainListAdapter(context: Context?, layout: Int, c: Cursor?, from: Array<out String>?, to: IntArray?, flags: Int, val currentID : Int) : SimpleCursorAdapter(context, layout, c, from, to, flags) {

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        super.bindView(view, context, cursor)
        if (view != null) {
            val buttonFovorit : Button = view.findViewById(R.id.buttonFavorit)
            if (cursor != null) {
                buttonFovorit.text = if (cursor.getInt(cursor.getColumnIndex(DBTables.Favorits.COLUMN_ID)) == 0) "" else "F"
                buttonFovorit.tag = ViewHolder(cursor.getInt(cursor.getColumnIndex(DBTables.Articles.COLUMN_ID)))
                buttonFovorit.setOnClickListener {
                    val tag: ViewHolder = it.tag as ViewHolder
                    if (context != null) {
                        val helper = DBHelper(context)
                        helper.changeFavorits(tag.id)
                        (it as Button).text = (if ((it as Button).text == "") "F" else "").toString()
                    }
                }
            }
            view.setOnClickListener {
                if (context != null) {
                    val buttonFovorit : Button = it.findViewById(R.id.buttonFavorit)
                    val tag: ViewHolder = buttonFovorit.tag as ViewHolder
                    if (DBHelper(context).getArticleIsArticle(tag.id)) {
                        val intent = Intent(context, ArticleActivity::class.java)
                        intent.putExtra("article_id", tag.id)
                        context.startActivity(intent)
                    } else {
                        val intent = Intent(context, MainActivity::class.java)
                        intent.putExtra("parent_id", tag.id)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return super.newView(context, cursor, parent)
    }

    class ViewHolder(val id : Int)

}