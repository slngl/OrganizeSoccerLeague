package com.slngl.organizesoccerleague.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slngl.organizesoccerleague.model.Match
import com.slngl.organizesoccerleague.model.Round
import com.slngl.organizesoccerleague.model.TeamsItem
import com.slngl.organizesoccerleague.repository.TeamsRepository
import com.slngl.organizesoccerleague.util.mapAndResponseIfSuccess
import com.slngl.organizesoccerleague.util.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val teamsRepository: TeamsRepository
) : ViewModel() {

    val liveTeams = MutableLiveData<List<TeamsItem?>>()
    val liveFixture = MutableLiveData<List<Round?>>()

    fun getFixture() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = teamsRepository.getTeams()

            response.toLiveData(liveTeams)

            response.mapAndResponseIfSuccess { teamList ->
                val homeRoundList = mutableListOf<Round>()
                val awayRoundList = mutableListOf<Round>()

                val teamSize = teamList.size
                var list = teamList.toMutableList()
                if (teamSize > 1) {
                    for (i in 0 until teamSize - 1) {
                        val matchListForHome = mutableListOf<Match>()
                        val matchListForAway = mutableListOf<Match>()
                        for (j in 0 until teamSize / 2) {
                            val secondIndex = teamSize - 1 - j
                            matchListForHome.add(
                                Match(
                                    list[j]?.teamName,
                                    list[secondIndex]?.teamName
                                )
                            )
                            matchListForAway.add(
                                Match(
                                    list[secondIndex]?.teamName,
                                    list[j]?.teamName
                                )
                            )
                        }

                        val round1 = Round(matchListForHome, i + 1)
                        homeRoundList.add(round1)
                        val round2 = Round(matchListForAway, i + teamSize)
                        awayRoundList.add(round2)

                        val newList: MutableList<TeamsItem?> = mutableListOf()
                        list[0]?.let { newList.add(it) }
                        newList.add(list[list.size - 1])

                        for (k in 1 until list.size - 1) {
                            newList.add(list[k])
                        }
                        list = newList
                    }
                } else {
                    homeRoundList.add(Round(listOf(), 0))
                }

                homeRoundList.addAll(awayRoundList)
                liveFixture.postValue(homeRoundList)
            }
        }
    }
}