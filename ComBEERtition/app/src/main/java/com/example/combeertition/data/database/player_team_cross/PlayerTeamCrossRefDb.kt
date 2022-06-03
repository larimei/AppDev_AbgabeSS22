package com.example.combeertition.data.database.player_team_cross

import androidx.room.Entity

@Entity(primaryKeys = ["teamId", "playerId"])
data class PlayerTeamCrossRefDb(
    val teamId: String,
    val playerId: String
)
