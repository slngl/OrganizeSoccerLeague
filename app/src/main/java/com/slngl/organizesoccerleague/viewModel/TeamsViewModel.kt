package com.slngl.organizesoccerleague.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slngl.organizesoccerleague.base.DataHolder
import com.slngl.organizesoccerleague.model.BaseResponse
import com.slngl.organizesoccerleague.repository.TeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val teamsRepository: TeamsRepository,
) : ViewModel() {

    val liveTeams = MutableLiveData<BaseResponse>()
    val liveError = MutableLiveData<String?>()

    fun getTeams() {
        viewModelScope.launch {
            val res = teamsRepository.getTeams()
            when (res) {
                is DataHolder.Success -> {
                    liveTeams.postValue(res.data)
                }
                is DataHolder.Error -> {
                    liveError.postValue(res.message)
                }
            }
        }
    }


}