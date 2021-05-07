package com.slngl.organizesoccerleague.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.slngl.organizesoccerleague.databinding.ItemTeamBinding
import com.slngl.organizesoccerleague.model.Team

class TeamsAdapter : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        val teamName = binding.tvTitle
        fun bindTo(item: Team?) {
            teamName.text = item?.name
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    fun submitList(itemList: List<Team>) {
        recyclerListDiffer.submitList(itemList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsAdapter.TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamBinding.inflate(inflater, parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamsAdapter.TeamViewHolder, position: Int) {
        val item = recyclerListDiffer.currentList.get(position)
        holder.bindTo(item)
    }

    override fun getItemCount(): Int {
        return recyclerListDiffer.currentList.size
    }
}