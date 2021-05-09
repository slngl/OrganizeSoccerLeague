package com.slngl.organizesoccerleague.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.slngl.organizesoccerleague.R
import com.slngl.organizesoccerleague.databinding.FragmentTeamsBinding
import com.slngl.organizesoccerleague.model.TeamsItem
import com.slngl.organizesoccerleague.ui.adapter.TeamsAdapter
import com.slngl.organizesoccerleague.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment(R.layout.fragment_teams) {

    private lateinit var viewModel: SharedViewModel

    private var fragmentTeamsBinding: FragmentTeamsBinding? = null

    //Adapter
    private val teamsAdapter = TeamsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val binding = FragmentTeamsBinding.bind(view)
        fragmentTeamsBinding = binding

        binding.rvTeams.adapter = teamsAdapter

        //get data from VM
        viewModel.getFixture()

        //observe team list
        viewModel.liveTeams.observe(viewLifecycleOwner, {
            it?.let { teamList ->
                teamsAdapter.submitList(teamList as List<TeamsItem>)
                binding.btDrawFixture.setOnClickListener {
                    findNavController().navigate(R.id.action_teamsFragment_to_fixtureFragment)
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTeamsBinding = null
    }

}