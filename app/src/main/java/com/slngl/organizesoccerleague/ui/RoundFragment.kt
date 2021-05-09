package com.slngl.organizesoccerleague.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slngl.organizesoccerleague.R
import com.slngl.organizesoccerleague.base.AppConstants.ARG_ROUND
import com.slngl.organizesoccerleague.databinding.FragmentRoundBinding
import com.slngl.organizesoccerleague.model.Match
import com.slngl.organizesoccerleague.ui.adapter.RoundAdapter
import com.slngl.organizesoccerleague.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoundFragment : Fragment(R.layout.fragment_round) {

    private var fragmentRoundBinding: FragmentRoundBinding? = null

    companion object {
        fun newInstance(round: Int) = RoundFragment()
            .apply { arguments = bundleOf(ARG_ROUND to round) }
    }

    private lateinit var viewModel: SharedViewModel

    private val roundAdapter = RoundAdapter()

    //Arguments
    private val argRoundId: Int by lazy {
        requireArguments().getInt(ARG_ROUND)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val binding = FragmentRoundBinding.bind(view)

        fragmentRoundBinding = binding

        binding.rvFixture.adapter = roundAdapter

        //observe round list
        viewModel.liveFixture.observe(viewLifecycleOwner, { roundList ->
            roundList.forEach { round ->
                if (argRoundId == 0) {
                    binding.textView.text = "There are not enough team on the list."
                } else if (argRoundId == round?.round) {
                    round.awayTeamList.let {
                        roundAdapter.submitList(it as List<Match>)
                        println("${round.round} " + it)
                    }
                    binding.textView.text = "${round.round}. WEEK"
                }

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRoundBinding = null
    }

}