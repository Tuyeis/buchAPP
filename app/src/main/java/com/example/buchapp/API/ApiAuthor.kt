package com.example.buchapp.API

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.buchapp.models.AuthorData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class ApiAuthor
{
    private val gson = Gson()
    private val client = OkHttpClient()
    private val imageCache = mutableMapOf<String, Bitmap>()
    fun getAuthor(authorId :String?): AuthorData? {

        val request = Request.Builder()
            .url("https://openlibrary.org/authors/$authorId.json")
            .build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()

        return gson.fromJson(responseBody, AuthorData::class.java)
    }
    suspend fun getImageAuthor(num:Int): Bitmap? = withContext(Dispatchers.IO) {

        val imageUrl = "https://covers.openlibrary.org/a/id/$num-M.jpg"

        // Primero, comprobamos si la imagen ya está en caché
        if (imageCache.containsKey(imageUrl)) {
            imageCache[imageUrl] // Devolvemos la imagen almacenada en caché
        } else {
            // Si la imagen no está en caché, la descargamos y la almacenamos en caché para futuras llamadas
            val request = Request.Builder().url(imageUrl).build()
            val response = client.newCall(request).execute()
            val inputStream = response.body?.byteStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageCache[imageUrl] = bitmap // Almacenamos la imagen en caché
            bitmap // Devolvemos la imagen descargada
        }
    }


}