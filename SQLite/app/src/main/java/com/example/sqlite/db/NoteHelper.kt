package com.example.sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.DatabaseContract.NoteColumns.Companion._ID
import java.sql.SQLException
import kotlin.jvm.Throws

class NoteHelper(context: Context) {
    private var databaseHelper : DatabaseHelper = DatabaseHelper(context)
    private lateinit var database : SQLiteDatabase


    companion object{
        private const val DATABASE_TABLE = DatabaseContract.NoteColumns.TABLE_NAME

        private var INSTANCE : NoteHelper? = null
        fun getInstance(context: Context):NoteHelper =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: NoteHelper(context)
            }
    }
    @Throws(SQLException::class)
    fun  open(){
        database = databaseHelper.writableDatabase
    }
    fun close(){
        databaseHelper.close()
        if (database.isOpen)
            database.close()
    }
    fun queryALL(): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }
    fun queryById(id: String):Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }
    fun insert(values: ContentValues?):Long{
        return database.insert(DATABASE_TABLE,null,values)
    }

    fun update(id: String,values: ContentValues?):Int{
        return database.update(DATABASE_TABLE,values,"$_ID = ?", arrayOf(id))
    }

    fun deleteId(id: String):Int{
        return database.delete(DATABASE_TABLE,"$_ID = '$id'",null)
    }
}