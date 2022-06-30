package com.example.combeertition.data.database.player

import androidx.room.*
import java.time.ZonedDateTime

@Entity(tableName = "player")
data class PlayerDb(
    @PrimaryKey
    val playerId: String,
    val name: String,
    val color: Int,
    val wins: Int,
    val looses: Int,
    val matches: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime
)
