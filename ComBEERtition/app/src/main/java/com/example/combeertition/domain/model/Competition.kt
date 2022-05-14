package com.example.combeertition.domain.model

import java.util.*

data class Competition(
    val id: String,
    val name: String,
    val date: Date, //maybe
    val color: String,
    val teams: List<Team>,
    val mode: String,
    val rounds: List<Round>
)
