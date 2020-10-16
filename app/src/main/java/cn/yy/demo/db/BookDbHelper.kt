package cn.yy.demo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

/**
 *    author : cy.wang
 *    date   : 2020/10/13
 *    desc   :
 */
@RequiresApi(Build.VERSION_CODES.P)
class BookDbHelper(
    context: Context?,
    name: String?,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {
    companion object {
        const val sql = "create table Books (" +
                "id integer primary key autoincrement, " +
                "name text );"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}