package com.slngl.organizesoccerleague.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.slngl.organizesoccerleague.db.Round

class FixtureAdapter(fragment: Fragment, val listOfPages: List<Round?>): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return listOfPages.size
    }

    override fun createFragment(position: Int): Fragment {
        val roundId = listOfPages.get(position)?.id
        return RoundFragment.newInstance(roundId!!)

    }
}