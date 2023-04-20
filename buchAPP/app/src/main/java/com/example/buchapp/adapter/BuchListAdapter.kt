package com.example.buchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.buchapp.databinding.RecyclerLayoutBinding
import com.example.buchapp.ui.models.Buch
import com.example.buchapp.ui.fragments.liste.BuchListAdapterListener


class BuchListAdapter(val buch: Array<Buch>, val listener: BuchListAdapterListener) :
    RecyclerView.Adapter<BuchListAdapter.ViewHolder>() {
    class ViewHolder(val binding: RecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Buch) {
            binding.tvElementName.text = item.titulo
            binding.IvRecycler.setImageDrawable(item.ImageResource?.let {
                ContextCompat.getDrawable(binding.root.context,
                    it
                )
            })
            binding.root.setOnClickListener { view ->
                // Handle click event
                // Get the id of the clicked Buch object
                val id = item.id

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int = buch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.binding
        cardView.tvElementName.text = buch[position].titulo
        val imageView = cardView.IvRecycler
        val image =
            buch[position].ImageResource?.let { ContextCompat.getDrawable(imageView.context, it) }
        imageView.setImageDrawable(image)

        holder.itemView.setOnClickListener{
            buch[position].id?.let { it1 -> listener.onBuchItemSelected(it1.toLong()) }
        }
    }


}