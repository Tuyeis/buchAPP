package com.example.buchapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.buchapp.R
import com.example.buchapp.databinding.ActivityBucherListeBinding
import com.example.buchapp.ui.fragments.liste.FavoritsFragment
import com.example.buchapp.ui.fragments.liste.HomeFragment
import com.example.buchapp.ui.fragments.liste.MailFragment
import com.example.buchapp.ui.fragments.liste.SearchFragment

class Bucher_Liste : AppCompatActivity() {

    lateinit var binding: ActivityBucherListeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBucherListeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(binding.fragment1buch.id, homeFragment)
                .commit()
            binding.bottomBar.selectedItemId = R.id.action_home
        }

        binding.bottomBar.setOnItemReselectedListener { item: MenuItem ->
            val fragment = when (item.itemId) {
                R.id.action_home-> HomeFragment()
                R.id.action_search -> SearchFragment()
                R.id.action_mail -> MailFragment()
                R.id.action_favorits -> FavoritsFragment()
                else -> Fragment()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(binding.fragment1buch.id, fragment)
            transaction.commit()
        }

    }
}