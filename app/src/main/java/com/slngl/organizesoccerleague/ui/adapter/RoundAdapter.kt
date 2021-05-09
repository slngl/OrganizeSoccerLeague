package com.slngl.organizesoccerleague.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.slngl.organizesoccerleague.databinding.ItemRoundBinding
import com.slngl.organizesoccerleague.model.Match

class RoundAdapter : RecyclerView.Adapter<RoundAdapter.RoundViewHolder>() {

    inner class RoundViewHolder(binding: ItemRoundBinding) : RecyclerView.ViewHolder(binding.root) {
        val homeTeamName = binding.tvHomeTeam
        val awayTeamName = binding.tvAwayTeam
        fun bindTo(item: Match?) {
            homeTeamName.text = item?.homeTeam
            awayTeamName.text = item?.awayTeam
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    fun submitList(itemList: List<Match>) {
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