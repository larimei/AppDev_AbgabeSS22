package com.example.combeertition.data.database.round

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "round")
data class RoundDb(
    @PrimaryKey
    val roundId: String,
    val round: String,
    val firstTeam: String,
    val secondTeam: String,
    val winner: String?,
    val loser: String?,
    val pointsFirst: Int,
    val pointsSecond: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)