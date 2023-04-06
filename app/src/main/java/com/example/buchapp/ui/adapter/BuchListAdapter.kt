package com.example.buchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.buchapp.databinding.RecyclerLayoutBinding
import com.example.buchapp.models.Buch


class BuchListAdapter(val buch: Array<Buch>) :
    RecyclerView.Adapter<BuchListAdapter.ViewHolder>() {
    class ViewHolder(val binding: RecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int = buch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.binding
        cardView.tvElementName.text = buch[position].name
        val imageView = cardView.IvRecycler
        val image = ContextCompat.getDrawable(imageView.context, buch[position].ImageResource)
        imageView.setImageDrawable(image)
    }


}