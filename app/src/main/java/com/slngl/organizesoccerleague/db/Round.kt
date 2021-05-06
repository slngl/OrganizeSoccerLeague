package com.slngl.organizesoccerleague.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "round_table")
data class Round(
    val matchList: List<String>,
    val round: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)