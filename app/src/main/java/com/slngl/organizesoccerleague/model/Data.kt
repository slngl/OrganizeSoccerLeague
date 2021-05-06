package com.slngl.organizesoccerleague.model

import com.slngl.organizesoccerleague.network.Team

data class Data(
        val next_page: String ?= null,
        val pages: Int ?= null,
        val prev_page: Boolean ?= null,
        val teams: List<Team> ?= null,
        val total: String ?= null
    )