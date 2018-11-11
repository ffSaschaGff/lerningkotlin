package com.example.saschag.lerningkotlin

import android.provider.BaseColumns

object DBTables {
    class Articles {
        companion object {
            val TABLE_NAME = "ARTIKLES"
            val COLUMN_ID = "_id"
            val COLUMN_TITLE = "_title"
            val COLUMN_CONTENT = "_content"
            val COLUMN_IS_ARTICLE = "_is_article"
            val COLUMN_PARENT = "_parent_id"
            val COLUMN_SORT = "_sort_field"
        }
    }
    class Favorits {
        companion object {
            val TABLE_NAME = "FAVORITES"
            val COLUMN_ID = "_id_fav"
        }
    }
}