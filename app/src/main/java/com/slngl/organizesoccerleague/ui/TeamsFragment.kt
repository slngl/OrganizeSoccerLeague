package com.slngl.organizesoccerleague.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.muddzdev.styleabletoast.StyleableToast
import com.slngl.organizesoccerleague.R
import com.slngl.organizesoccerleague.databinding.FragmentTeamsBinding
import com.slngl.organizesoccerleague.ui.adapter.TeamsAdapter
import com.slngl.organizesoccerleague.util.getColorFromAttr
import com.slngl.organizesoccerleague.viewModel.TeamsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment(R.layout.fragment_teams) {

    private lateinit var viewModel: TeamsViewModel

    private var fragmentTeamsBinding: FragmentTeamsBinding? = null

    //Adapter
    private val teamsAdapter = TeamsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(TeamsViewModel::class.java)

        val binding = FragmentTeamsBinding.bind(view)
        fragmentTeamsBinding = binding

        //get data from VM
        viewModel.getTeams()

        binding.rvTeams.adapter = teamsAdapter

        //observe team list
        viewModel.liveTeams.observe(viewLifecycleOwner, {
            it.teams?.let { teamList ->
                teamsAdapter.submitList(teamList)
                viewModel.drawFixture(teamList)
            }
        })

        //observe error message
        viewModel.liveError.observe(viewLifecycleOwner, {
            if (it != null) {
                StyleableToast.Builder(requireContext())
                    .text("Please check your network connection")
                    .textBold()
                    .textColor(requireContext().getColorFromAttr(R.attr.titleTextColor))
                    .backgroundColor(requireContext().getColorFromAttr(R.attr.errorEnabled))
                    .show()
            }
        })

        binding.btDrawFixture.setOnClickListener {
            findNavController().navigate(R.id.action_teamsFragment_to_fixtureFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTeamsBinding = null
    }

}