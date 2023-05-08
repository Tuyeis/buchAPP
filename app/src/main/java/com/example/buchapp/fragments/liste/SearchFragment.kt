package com.example.buchapp.fragments.liste

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.buchapp.BuildConfig
import com.example.buchapp.R
import com.example.buchapp.activities.InfoAuthorActivity
import com.example.buchapp.databinding.FragmentSearchBinding
import com.example.buchapp.API.Api
import com.example.buchapp.data.BuchMemoDatasource
import com.example.buchapp.models.Buchern
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var api: Api
    private lateinit var buchMemo: BuchMemoDatasource

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build()
            )
        }
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupSearchButton()
        return binding.root
    }

    private fun setupSearchButton() {
        binding.btnSearchBook.setOnClickListener {
            val buchN = binding.nummerBook.text.toString()
            val buchS = binding.etSearchBook.text.toString()

            if (buchN.isEmpty() || buchS.isEmpty()) {
                Toast.makeText(requireContext(), "Algun campo está vacío", Toast.LENGTH_SHORT)
                    .show()
            } else {
                api = apiInitialise()
                CoroutineScope(Dispatchers.IO).launch {
                    val arrayLenght = api.arraySize()
                    if (buchN.toInt() >= arrayLenght || buchN.toInt() < 0) {
                        withContext(Dispatchers.Main) {
                            binding.BookNumberED.text = "Max $arrayLenght"
                        }
                    } else {

                        val buch = api.getBuchern()
                        val bookId = searchBookId()
                        withContext(Dispatchers.Main) {
                            displayBookInfo(buch, bookId)
                        }

                    }

                }
            }
        }
    }

    private fun apiInitialise(): Api {
        return Api(
            binding.etSearchBook.text.toString(),
            binding.nummerBook.text.toString().toInt(),
            requireContext()
        )
    }

    private fun searchBookId(): String? {
        return api.searchBuchern()
    }

    private fun displayBookInfo(buch: Buchern, bookId: String?) {
        binding.tvTitle.text = buch.title.toString()
        val author = buch.authors
        if (author == null) {
            binding.bookAuthor.text = buch.title.toString()

        } else {
            binding.bookAuthor.text = author[0].toString()
            binding.bookAuthor.setOnClickListener {

                Toast.makeText(context, "Es gibt kein Author", Toast.LENGTH_SHORT)
                    .show()


                val id = author[0].key?.substringAfterLast("/") ?: "OL23919A"
                val intent = Intent(
                    activity, InfoAuthorActivity::class.java
                ).putExtra("idAuthor", id)
                startActivity(intent)

            }


        }
        binding.urlButton.setOnClickListener {
            openBookUrl(bookId)
        }
        setImage()
        buchMemo = BuchMemoDatasource(requireContext())
        binding.buchSpeichern.setOnClickListener {
            saveBookInDatabase(bookId, buch.title.toString())
        }
    }


    private fun openBookUrl(bookId: String?) {
        if (bookId == null) {
            Toast.makeText(context, "ein Buch auswählen", Toast.LENGTH_SHORT).show()
        } else {
            val url = "https://openlibrary.org/works/$bookId"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    private fun saveBookInDatabase(bookId: String?, title: String) {
        if (bookId == null) return
        if (buchMemo.getIdlibro(title) == bookId) {
            Toast.makeText(
                context, "Du hast schon das Buch in deiner List", Toast.LENGTH_SHORT
            ).show()
        } else {
            buchMemo.insertLibro(title, bookId)
        }
    }

    private fun setImage() {
        CoroutineScope(Dispatchers.IO).launch {
            val image = api.getImageFromId()

            withContext(Dispatchers.Main) {
                if (image == null || image.width == 1 || image.height == 1) {
                    binding.ivBookCover.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(), R.drawable.books_main
                        )
                    )
                } else {
                    binding.ivBookCover.setImageBitmap(image)
                }
            }
        }

    }
}