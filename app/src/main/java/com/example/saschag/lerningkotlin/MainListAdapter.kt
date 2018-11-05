package com.example.saschag.lerningkotlin

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter

class MainListAdapter(context: Context?, layout: Int, c: Cursor?, from: Array<out String>?, to: IntArray?, flags: Int) : SimpleCursorAdapter(context, layout, c, from, to, flags) {

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        super.bindView(view, context, cursor)
    }

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return super.newView(context, cursor, parent)
    }
}