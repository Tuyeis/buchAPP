package com.example.buchapp.ui.fragments.liste

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.buchapp.R
import com.example.buchapp.databinding.FragmentSearchBinding
import com.example.buchapp.ui.API.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFragment : Fragment() {

lateinit var binding: FragmentSearchBinding
    lateinit var api: Api

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSearchBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        binding.btnSearchBook.setOnClickListener {
            api = Api(binding.etSearchBook.text.toString(),context)



            CoroutineScope(Dispatchers.IO).launch {
                val buch = api.getBuchern()

                    withContext(Dispatchers.Main) {
                        binding.tvTitle.text =buch.title.toString()
                        binding.bookSynopsis.text= buch.description.toString()

                    }

                val image = api.getImageFromId()


                    withContext(Dispatchers.Main) {
                        if(image == null || image.width == 1 || image.height == 1){
                            binding.ivBookCover.setImageDrawable(context?.let { it1 ->
                                ContextCompat.getDrawable(
                                    it1, R.drawable.books_main
                                )
                            })
                        } else {
                            binding.ivBookCover.setImageBitmap(image)
                        }


                    }



            }
        }
        return binding.root
    }
}