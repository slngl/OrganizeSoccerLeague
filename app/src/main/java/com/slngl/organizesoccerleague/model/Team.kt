package com.slngl.organizesoccerleague.model

data class Team(
    val country: Country,
    val federation: List<Any>,
    val id: String,
    val name: String,
    val stadium: String,
    val translations: Any?
)