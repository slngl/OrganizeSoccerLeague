package com.slngl.organizesoccerleague.repository

import com.slngl.organizesoccerleague.db.Round
import com.slngl.organizesoccerleague.db.RoundDao
import javax.inject.Inject

class RoundRepository @Inject constructor(private val dao: RoundDao) {

    suspend fun insertRound(round: Round) = dao.insertRound(round)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun getAllRounds() = dao.getAllRounds()

    suspend fun getRound(roundId: Int) = dao.getRound(roundId)

}