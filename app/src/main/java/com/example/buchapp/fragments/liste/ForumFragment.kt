package com.example.buchapp.fragments.liste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buchapp.adapter.ForumAdapter
import com.example.buchapp.databinding.FragmentForumBinding
import com.example.buchapp.models.Topic


class ForumFragment : Fragment() {

    lateinit var binding: FragmentForumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForumBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment



        binding.elementList.adapter = ForumAdapter(Topic.topic)
        //recycler horizontal
        binding.elementList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        return binding.root
    }

}
data class User(val name:String, val first:String, val value:String){
    constructor():this("","","")
}