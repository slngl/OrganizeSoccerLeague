package com.slngl.organizesoccerleague.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slngl.organizesoccerleague.base.DataHolder
import com.slngl.organizesoccerleague.db.Round
import com.slngl.organizesoccerleague.model.Data
import com.slngl.organizesoccerleague.model.Team
import com.slngl.organizesoccerleague.repository.RoundRepository
import com.slngl.organizesoccerleague.repository.TeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val teamsRepository: TeamsRepository,
    private val roundRepository: RoundRepository
) : ViewModel() {

    val liveTeams = MutableLiveData<Data>()
    val liveError = MutableLiveData<String?>()

    fun getTeams() {
        viewModelScope.launch {
            val res = teamsRepository.getTeams()
            when (res) {
                is DataHolder.Success -> {
                    liveTeams.postValue(res.data.data)
                }
                is DataHolder.Error -> {
                    liveError.postValue(res.message)
                }
            }
        }
    }

    fun insertRound(round: Round) {
        viewModelScope.launch {
            roundRepository.insertRound(round = round)
        }
    }

    fun clearAllRounds() {
        viewModelScope.launch {
            roundRepository.deleteAll()
        }
    }

    fun drawFixture(teamList: List<Team>) {
        clearAllRounds()
        val teamSize = teamList.size
        var list = teamList.toMutableList()

        for (i in 0 until teamSize - 1) {
            val homeTeamList = mutableListOf<String>()
            for (j in 0 until teamSize / 2) {
                val secondIndex = teamSize - 1 - j
                homeTeamList.add(j, list[j].name + "///" + list[secondIndex].name)
            }

            val r = Round(homeTeamList, i + 1)
            insertRound(r)

            val newList: MutableList<Team> = mutableListOf()
            newList.add(list[0])
            newList.add(list[list.size - 1])

            for (k in 1 until list.size - 1) {
                newList.add(list[k])
            }
            list = newList
        }

        list = teamList.toMutableList()

        for (i in 0 until teamSize - 1) {
            val homeTeamList = mutableListOf<String>()
            for (j in 0 until teamSize / 2) {
                val secondIndex = teamSize - 1 - j
                homeTeamList.add(j, list[secondIndex].name + "///" + list[j].name)
            }

            val r = Round(homeTeamList, i + teamSize)
            insertRound(r)

            val newList: MutableList<Team> = mutableListOf()
            newList.add(list[0])
            newList.add(list[list.size - 1])

            for (k in 1 until list.size - 1) {
                newList.add(list[k])
            }
            list = newList
        }
    }
}