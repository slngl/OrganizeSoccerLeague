package com.slngl.organizesoccerleague.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.slngl.organizesoccerleague.R
import com.slngl.organizesoccerleague.databinding.FragmentTeamsBinding

class TeamsFragment : Fragment(R.layout.fragment_teams) {

    private var fragmentTeamsBinding: FragmentTeamsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTeamsBinding.bind(view)
        fragmentTeamsBinding = binding


    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTeamsBinding = null
    }

}