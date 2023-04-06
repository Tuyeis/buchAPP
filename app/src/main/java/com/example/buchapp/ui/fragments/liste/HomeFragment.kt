package com.example.buchapp.ui.fragments.liste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buchapp.databinding.FragmentHomeBinding
import com.example.buchapp.models.Buch
import com.example.buchapp.ui.adapter.BuchListAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        binding.elementList.adapter = BuchListAdapter(Buch.bucher)
        binding.elementList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        return binding.root
    }


}