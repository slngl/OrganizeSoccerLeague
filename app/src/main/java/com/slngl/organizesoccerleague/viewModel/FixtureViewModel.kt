package com.slngl.organizesoccerleague.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slngl.organizesoccerleague.db.Round
import com.slngl.organizesoccerleague.repository.RoundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixtureViewModel @Inject constructor(private val repository: RoundRepository) : ViewModel() {

    val liveFixture = MutableLiveData<List<Round>>()

    fun getFixture() {
        viewModelScope.launch {
            val rounds = repository.getAllRounds()
            liveFixture.postValue(rounds)
        }
    }
}