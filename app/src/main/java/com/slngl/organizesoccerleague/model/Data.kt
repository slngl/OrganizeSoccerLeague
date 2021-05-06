package com.slngl.organizesoccerleague.model

data class Data(
        val next_page: String ?= null,
        val pages: Int ?= null,
        val prev_page: Boolean ?= null,
        val teams: List<Team> ?= null,
        val total: String ?= null
    )