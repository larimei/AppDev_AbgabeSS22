package com.example.combeertition.domain.model

data class Round(
    val id: String,
    val firstTeam: Team,
    val opponentTeam: Team,
    val round: Int,  //Achtel, Viertel, Halbfinale etc.
    val winner: Team
)

