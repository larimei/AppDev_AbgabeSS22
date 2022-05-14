package com.example.combeertition.domain.model

data class Round(
    val id: String,
    val firstTeam: String,
    val opponentTeam: String,
    val round: Int,  //Achtel, Viertel, Halbfinale etc.
    val winner: String
)

