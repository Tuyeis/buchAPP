package com.example.buchapp.ui.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BuchDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    companion object {
        const val DATABASE_NAME = "libros.db"
        const val DATABASE_VERSION = 1

        const val TABLE_USUARIO = "usuario"
        const val COLUMN_ID_USUARIO = "_id"
        const val COLUMN_USUARIO_NOMBRE = "nombre"
        const val COLUMN_USUARIO_CONTRASENA = "contrasena"

        const val TABLE_LIBROS = "libros"
        const val COLUMN_LIBROS_ID = "_id"
        const val COLUMN_LIBROS_NOMBRE = "nombre"
        const val COLUMN_LIBROS_ID_API = "id_api"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsuarioTable = """
            CREATE TABLE $TABLE_USUARIO(
                $COLUMN_ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT ,
                $COLUMN_USUARIO_NOMBRE TEXT NOT NULL,
                $COLUMN_USUARIO_CONTRASENA TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createUsuarioTable)

        val createLibrosTable = """
            CREATE TABLE $TABLE_LIBROS (
                $COLUMN_LIBROS_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_LIBROS_NOMBRE TEXT NOT NULL,
                $COLUMN_LIBROS_ID_API TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createLibrosTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LIBROS")
        onCreate(db)
    }
}