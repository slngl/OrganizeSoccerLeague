package com.slngl.organizesoccerleague.network

import com.slngl.organizesoccerleague.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface LiveScoreApiService {

//    https://livescore-api.com/api-client/teams/list.json?key=rSE51PkvI6DJlLWI&secret=ZgrF4NBfCOcYGck85Zd6PxByEfhZ1dI1

    @GET("teams/list.json?key=rSE51PkvI6DJlLWI&secret=ZgrF4NBfCOcYGck85Zd6PxByEfhZ1dI1")
    suspend fun getTeams() : Response<BaseResponse>
}