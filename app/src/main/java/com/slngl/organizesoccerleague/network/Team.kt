package com.slngl.organizesoccerleague.network

import com.slngl.organizesoccerleague.model.Country

data class Team(
    val country: Country,
    val federation: List<Any>,
    val id: String,
    val name: String,
    val stadium: String,
    val translations: Any?
        )