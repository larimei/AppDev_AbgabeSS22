package com.example.combeertition.domain.model

data class Team(
    val id: String,
    val name: String,
    val players: List<Player>,
    val color: String
)
