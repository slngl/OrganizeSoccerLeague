package com.slngl.organizesoccerleague.repository

import com.slngl.organizesoccerleague.base.BaseRepository
import com.slngl.organizesoccerleague.base.DataHolder
import com.slngl.organizesoccerleague.model.TeamsItem
import com.slngl.organizesoccerleague.network.LiveScoreApiService
import javax.inject.Inject

class TeamsRepository @Inject constructor(val api: LiveScoreApiService) : BaseRepository() {

    suspend fun getTeams(): DataHolder<List<TeamsItem?>> {
        return safeApiCall({ api.getTeams() }, "getTeams Error")
    }
}