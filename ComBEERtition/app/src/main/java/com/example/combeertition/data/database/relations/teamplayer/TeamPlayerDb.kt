package com.example.combeertition.data.database.relations.teamplayer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "teamPlayer")
data class TeamPlayerDb(
    @PrimaryKey
    val teamPlayerId: String,
    val teamId: String,
    val playerId: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime
)
