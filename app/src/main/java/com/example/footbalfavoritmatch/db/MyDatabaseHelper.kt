package com.example.footbalfavoritmatch.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseHelper (ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){
    companion object {

        private var instance: MyDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseHelper {
            if (instance == null) {
                instance = MyDatabaseHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY+ AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.HOME_TEAM to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_SCORE to  TEXT,
            Favorite.DATE to TEXT

            )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseHelper
get() = MyDatabaseHelper.getInstance(applicationContext)