package com.example.buchapp.ui.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.buchapp.ui.API.Api


class BuchMemoDatasource(context: Context) {

    private val dbHelper: BuchDatabaseHelper = BuchDatabaseHelper(context)
    private var database: SQLiteDatabase? = null



    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun insertUsuario(nombre: String, contrasena: String): Long {
        open()
        val values = ContentValues().apply {
            put(BuchDatabaseHelper.COLUMN_USUARIO_NOMBRE, nombre)
            put(BuchDatabaseHelper.COLUMN_USUARIO_CONTRASENA, contrasena)
        }
        return database?.insert(BuchDatabaseHelper.TABLE_USUARIO, null, values) ?: -1
    }
    fun getUsuario(nombreUsuario: String, contrasena: String): Boolean {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BuchDatabaseHelper.COLUMN_ID_USUARIO)
        val selection = "${BuchDatabaseHelper.COLUMN_USUARIO_NOMBRE} = ? AND ${BuchDatabaseHelper.COLUMN_USUARIO_CONTRASENA} = ?"
        val selectionArgs = arrayOf(nombreUsuario, contrasena)

        val cursor = db.query(
            BuchDatabaseHelper.TABLE_USUARIO,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val existeUsuario = cursor.moveToFirst()

        cursor.close()
        db.close()

        return existeUsuario
    }
    fun getIdlibro(nombreLibro: String): String? {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(BuchDatabaseHelper.COLUMN_LIBROS_ID_API)
        val selection =  "${BuchDatabaseHelper.COLUMN_LIBROS_NOMBRE} = ?"
        val selectionArgs = arrayOf(nombreLibro)

        val cursor = db.query(
            BuchDatabaseHelper.TABLE_LIBROS,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        var idLibro: String? = null
        cursor.use { c ->
            if (c.moveToFirst()) {
                idLibro = c.getString(c.getColumnIndexOrThrow(BuchDatabaseHelper.COLUMN_LIBROS_ID_API))
            }
        }
        if(idLibro == null){
            return nombreLibro
        }
        return idLibro
    }
    fun insertLibro(nombre: String, idApi: String): Long {
        val values = ContentValues().apply {
            put(BuchDatabaseHelper.COLUMN_LIBROS_NOMBRE, nombre)
            put(BuchDatabaseHelper.COLUMN_LIBROS_ID_API, idApi)
        }
        return database?.insert(BuchDatabaseHelper.TABLE_LIBROS, null, values) ?: -1
    }

}


