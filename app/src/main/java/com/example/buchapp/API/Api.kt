package com.example.buchapp.API

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.buchapp.data.BuchMemoDatasource
import com.example.buchapp.models.Buchern
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Api(buchName: String, buchNummer: Int, context: Context?) {


    private val db: BuchMemoDatasource? = context?.let { BuchMemoDatasource(it) }

    private val buchName: String = buchName
    private val buchNummer: Int = buchNummer

    private val gson = Gson()
    private val client = OkHttpClient()
    private val imageCache = mutableMapOf<String, Bitmap>()


    fun getBuchern(): Buchern {

        // aqui buscamos de forma sencilla un libro por su id y devolvemos  lel objeto buch

        val id_buch = searchBuchern()
        val request = Request.Builder()
            .url("https://openlibrary.org/works/$id_buch.json")
            .build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()

        return gson.fromJson(responseBody, Buchern::class.java)
    }
    fun arraySize():Int{
        val request = Request.Builder()
            .url("https://openlibrary.org/search.json?q=$buchName&lang=eng")
            .build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        val jsonObject = responseBody?.let { JSONObject(it) }

        // seed esta dentro de docs por lo que primero hay que sacar el array dosc y luego dentro seed
        val seedArray = jsonObject?.getJSONArray("docs")?.getJSONObject(0)?.getJSONArray("seed")

        return seedArray!!.length()

    }

    fun searchBuchern(): String? {
        val request = Request.Builder()
            .url("https://openlibrary.org/search.json?q=$buchName&lang=eng")
            .build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        val jsonObject = responseBody?.let { JSONObject(it) }

        // Si el JSON no tiene los campos esperados, devuelve nulo
        if (!jsonObject!!.has("docs") || jsonObject.getJSONArray("docs").length() == 0) {
            return "No hay libro"
        }

        // seed esta dentro de docs por lo que primero hay que sacar el array dosc y luego dentro seed
        val seedArray = jsonObject.getJSONArray("docs").getJSONObject(0).getJSONArray("seed")
        return seedArray.getString(buchNummer).substring(7)
    }


    suspend fun getImageFromId(): Bitmap? = withContext(Dispatchers.IO) {
        val bookId = searchBuchern()
        val imageUrl = "https://covers.openlibrary.org/b/olid/$bookId-M.jpg"

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
