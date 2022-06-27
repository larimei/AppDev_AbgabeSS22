package com.example.combeertition.data.database.relations.competitionteam

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "competitionTeam")
data class CompetitionTeamDb(
    @PrimaryKey
    val competitionTeamId: String,
    val competitionId: String,
    val teamId: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime
)
