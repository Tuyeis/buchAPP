package com.example.buchapp.models

import com.example.buchapp.R

class Buch(
    val name: String,
    val ImageResource: Int
) {
    companion object {
        val bucher = arrayOf(
            Buch("Buch1", R.drawable.eins_buch),
            Buch("Buch2", R.drawable.zwei_buch),
            Buch("Buch3", R.drawable.drei_buch),
            Buch("Buch4", R.drawable.vier_buch),
            Buch("Buch5", R.drawable.funf_buch),
            Buch("Buch6", R.drawable.sechs_buch)
        )
    }
}