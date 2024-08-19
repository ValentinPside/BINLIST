package com.example.binlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.binlist.R
import com.example.binlist.databinding.CardItemBinding
import com.example.binlist.domain.models.RvCard

class Adapter : ListAdapter<RvCard, Adapter.ViewHolder>(DiffUtilHotel()) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardItemBinding.bind(itemView)

        fun bind(card: RvCard) {
            binding.scheme.text = card.scheme
            binding.number.text = card.number
            binding.countryText.text = card.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = currentList[position]
        holder.bind(card)
    }

    private class DiffUtilHotel : DiffUtil.ItemCallback<RvCard>() {

        override fun areItemsTheSame(oldItem: RvCard, newItem: RvCard): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RvCard, newItem: RvCard): Boolean {
            return oldItem == newItem
        }
    }
}