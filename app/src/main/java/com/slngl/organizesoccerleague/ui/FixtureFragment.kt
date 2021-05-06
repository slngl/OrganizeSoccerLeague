package com.slngl.organizesoccerleague.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slngl.organizesoccerleague.R
import com.slngl.organizesoccerleague.databinding.FragmentFixtureBinding
import com.slngl.organizesoccerleague.ui.adapter.FixtureAdapter
import com.slngl.organizesoccerleague.viewModel.FixtureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FixtureFragment : Fragment(R.layout.fragment_fixture) {

    private lateinit var viewModel: FixtureViewModel

    private var fragmentFixtureBinding: FragmentFixtureBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFixtureBinding.bind(view)
        fragmentFixtureBinding = binding

        viewModel = ViewModelProvider(requireActivity()).get(FixtureViewModel::class.java)

        //get data from VM
        viewModel.getFixture()

        //observe round list for viewPager
        viewModel.liveFixture.observe(viewLifecycleOwner,{
            if (binding.vpPages.adapter == null) {
                val adapter = FixtureAdapter(this, it)
                binding.vpPages.adapter = adapter
                binding.vpPages.offscreenPageLimit = adapter.itemCount
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentFixtureBinding = null
    }
}