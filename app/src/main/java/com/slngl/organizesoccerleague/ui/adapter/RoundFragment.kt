package com.slngl.organizesoccerleague.ui.adapter

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slngl.organizesoccerleague.R
import com.slngl.organizesoccerleague.databinding.FragmentRoundBinding
import com.slngl.organizesoccerleague.viewModel.FixtureViewModel

const val ARG_ROUND = "arg.round"

class RoundFragment : Fragment(R.layout.fragment_round) {

    private var fragmentRoundBinding: FragmentRoundBinding? = null

    companion object {
        fun newInstance(round: Int) = RoundFragment()
            .apply { arguments = bundleOf(ARG_ROUND to round) }
    }

    private lateinit var viewModel: FixtureViewModel

    //Arguments
    private val argRoundId: Int by lazy {
        requireArguments().getInt(ARG_ROUND)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FixtureViewModel::class.java)

        val binding = FragmentRoundBinding.bind(view)

        fragmentRoundBinding = binding

        //get round list from VM
        viewModel.getFixture()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRoundBinding = null
    }

}