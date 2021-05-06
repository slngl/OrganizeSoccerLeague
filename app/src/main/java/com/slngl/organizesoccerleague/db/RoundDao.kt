package com.slngl.organizesoccerleague.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoundDao {

    @Insert
    suspend fun insertRound(round: Round)

    @Query("DELETE FROM round_table")
    suspend fun deleteAll();

    @Query("SELECT * FROM round_table WHERE id=:id")
    suspend fun getRound(id: Int): Round

    @Query("SELECT * FROM round_table")
    suspend fun getAllRounds(): List<Round>
}