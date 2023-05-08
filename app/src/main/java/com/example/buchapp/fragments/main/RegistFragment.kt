package com.example.buchapp.fragments.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.buchapp.R
import com.example.buchapp.databinding.FragmentRegistBinding
import com.example.buchapp.activities.Bucher_Liste
import com.example.buchapp.data.BuchMemoDatasource


class RegistFragment : Fragment() {

    lateinit var binding : FragmentRegistBinding
    private lateinit var datasource: BuchMemoDatasource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegistBinding.inflate(layoutInflater)
        datasource = BuchMemoDatasource(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        returnBtn()
        register()
    }
    private fun returnBtn(){
        binding.tvVolver.setOnClickListener {

            findNavController().navigate(R.id.action_registFragment_to_blankFragment)
        }
    }
    private fun register()
    {
        binding.btnRegistrar.setOnClickListener{

            val exist : Boolean = datasource.getUsuario(binding.etUserName.text.toString(),binding.etPass.text.toString())
            if(exist){
                Toast.makeText(activity,"Der Benutzer ist schon registriert", Toast.LENGTH_SHORT).show()

            }else{

                datasource.insertUsuario(binding.etUserName.text.toString(),binding.etPass.text.toString())

                val intent = Intent(activity, Bucher_Liste:: class.java)
                startActivity(intent)
            }

        }
    }

    }