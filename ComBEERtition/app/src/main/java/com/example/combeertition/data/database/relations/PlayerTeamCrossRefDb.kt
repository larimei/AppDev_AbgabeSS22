package com.example.combeertition.data.database.relations

import androidx.room.Entity

@Entity(primaryKeys = ["teamId", "playerId"])
data class PlayerTeamCrossRefDb(
    val teamId: String,
    val playerId: String
)
