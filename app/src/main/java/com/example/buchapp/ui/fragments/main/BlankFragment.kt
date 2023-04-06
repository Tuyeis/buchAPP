package com.example.buchapp.ui.fragments.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.buchapp.ui.activities.Bucher_Liste
import com.example.buchapp.R
import com.example.buchapp.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
lateinit var binding: FragmentBlankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBlankBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRegistrer.setOnClickListener{

            findNavController().navigate(R.id.action_blankFragment_to_registFragment)
        }
        binding.btnLogin.setOnClickListener{
            val intent = Intent(activity, Bucher_Liste:: class.java)
            startActivity(intent)
        }



    }
}

