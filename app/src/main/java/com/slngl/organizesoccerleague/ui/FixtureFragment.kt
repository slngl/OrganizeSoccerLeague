package com.slngl.organizesoccerleague.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.slngl.organizesoccerleague.R
import com.slngl.organizesoccerleague.databinding.FragmentFixtureBinding

class FixtureFragment : Fragment(R.layout.fragment_fixture) {

    private var fragmentFixtureBinding: FragmentFixtureBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFixtureBinding.bind(view)
        fragmentFixtureBinding = binding


    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentFixtureBinding = null
    }
}