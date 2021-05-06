package com.slngl.organizesoccerleague.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.slngl.organizesoccerleague.databinding.ItemRoundBinding

class RoundAdapter : RecyclerView.Adapter<RoundAdapter.RoundViewHolder>() {

    inner class RoundViewHolder(binding: ItemRoundBinding) : RecyclerView.ViewHolder(binding.root) {
        val homeTeamName = binding.tvHomeTeam
        val awayTeamName = binding.tvAwayTeam
        fun bindTo(item: String?) {

            val splited = item?.split("///")

            splited?.let {
                homeTeamName.text = splited.get(0)
                awayTeamName.text = splited.get(1)
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    fun submitList(itemList: List<String>) {
        recyclerListDiffer.submitList(itemList)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoundAdapter.RoundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRoundBinding.inflate(inflater, parent, false)
        return RoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoundAdapter.RoundViewHolder, position: Int) {
        val item = recyclerListDiffer.currentList.get(position)
        holder.bindTo(item)
    }

    override fun getItemCount() = recyclerListDiffer.currentList.size

}