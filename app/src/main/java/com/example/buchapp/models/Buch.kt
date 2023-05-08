package com.example.buchapp.models

import com.example.buchapp.R

class Buch(
    val id: Int? = null,
    val titulo: String? = null,
    val ImageResource: Int? = null,
    val autor: String? = null,


) {
    companion object {
        val bucher = arrayOf(
            Buch(1,"Juego de tronos", R.drawable.eins_buch),
            Buch(2,"Juego de tronos", R.drawable.zwei_buch),
            Buch(3,"Juego de tronos", R.drawable.drei_buch),
            Buch(4,"Juego de tronos", R.drawable.vier_buch),
            Buch(5,"Juego de tronos", R.drawable.funf_buch),
            Buch(6,"Juego de tronos", R.drawable.sechs_buch)
        )
    }
}