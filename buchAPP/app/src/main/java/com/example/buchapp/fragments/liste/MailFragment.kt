package com.example.buchapp.ui.fragments.liste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buchapp.databinding.FragmentMailBinding


class MailFragment : Fragment() {


   lateinit var binding: FragmentMailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


}