package com.example.buchapp.ui.models

import com.example.buchapp.R

class Buch(
    val id: Int? = null,
    val titulo: String? = null,
    val ImageResource: Int? = null,
    val autor: String? = null,
    val sinopsis: String? = null,
    val genero: String? = null,
    val publisher: String? = null,
    val year: Int? = null,
    val created_at: String? = null,
    val updated_at: String? = null

) {
    companion object {
        val bucher = arrayOf(
            Buch(1,"Buch1", R.drawable.eins_buch),
            Buch(2,"Buch2", R.drawable.zwei_buch),
            Buch(3,"Buch3", R.drawable.drei_buch),
            Buch(4,"Buch4", R.drawable.vier_buch),
            Buch(5,"Buch5", R.drawable.funf_buch),
            Buch(6,"Buch6", R.drawable.sechs_buch)
        )
    }
}