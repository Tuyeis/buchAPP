package com.example.buchapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.buchapp.databinding.ActivityMainBinding
import com.example.buchapp.ui.data.BuchMemoDatasource


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    private lateinit var datasource: BuchMemoDatasource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        datasource = BuchMemoDatasource(this)

    }
    override  fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart: DataSouurce wird geöffnet")
        datasource.open()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onStop: Datasourde wird geschlossen")
        datasource.close()
    }



}