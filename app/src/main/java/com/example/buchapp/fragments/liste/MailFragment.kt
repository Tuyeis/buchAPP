package com.example.buchapp.fragments.liste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buchapp.adapter.BuchlistDataAdapter

import com.example.buchapp.databinding.FragmentMailBinding


class MailFragment : Fragment() {

    lateinit var binding: FragmentMailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMailBinding.inflate(inflater, container, false)

        // Observamos los cambios en el PagingData y actualizamos el adapter

        binding.mailList.adapter = BuchlistDataAdapter(requireContext())

        binding.mailList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        return binding.root

    }


}