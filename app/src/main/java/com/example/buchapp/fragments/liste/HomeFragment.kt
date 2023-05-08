package com.example.buchapp.fragments.liste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buchapp.databinding.FragmentHomeBinding
import com.example.buchapp.models.Buch
import com.example.buchapp.activities.BuchVorlage
import com.example.buchapp.adapter.BuchListAdapter


class HomeFragment : Fragment(), BuchListAdapterListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)

        binding.elementList.adapter = BuchListAdapter(Buch.bucher,this)
        binding.elementList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        return binding.root
    }
    override fun onBuchItemSelected(id: Long) {
        Toast.makeText(context, "$id", Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, BuchVorlage:: class.java).apply { putExtra("buch_id",id) }
        startActivity(intent)
    }
}
interface BuchListAdapterListener {
    fun onBuchItemSelected(id: Long)
}
