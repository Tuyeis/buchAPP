package com.example.buchapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.buchapp.databinding.ActivityBuchVorlageBinding
import com.example.buchapp.ui.models.Buch


class BuchVorlage : AppCompatActivity() {
    lateinit var binding: ActivityBuchVorlageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuchVorlageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val buchId = intent.getLongExtra("buch_id",-1)
        val buch = Buch.bucher.find { it.id == buchId.toInt() } // Busca el libro por el ID en el array de libros
        buch?.let {

            it.ImageResource?.let { it1 -> binding.bookCover.setImageResource(it1) }
            binding.bookAuthor.text = it.autor
            binding.bookSynopsis.text = it.sinopsis
            binding.bookTitle.text = it.titulo
        }

    }
}