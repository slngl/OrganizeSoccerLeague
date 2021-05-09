package com.slngl.organizesoccerleague.network

import com.slngl.organizesoccerleague.model.TeamsItem
import retrofit2.Response
import retrofit2.http.GET

interface LiveScoreApiService {

    @GET("api/team")
    suspend fun getTeams(): Response<List<TeamsItem?>>
}