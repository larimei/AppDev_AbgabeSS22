package com.example.combeertition.data.database.competition

import androidx.room.*
import com.example.combeertition.data.database.relations.CompetitionRoundCrossRefDb
import com.example.combeertition.data.database.relations.CompetitionTeamCrossRefDb
import com.example.combeertition.data.database.round.RoundDb
import com.example.combeertition.data.database.team.TeamDb
import java.time.ZonedDateTime

@Entity(tableName = "competition")
data class CompetitionDb(
    @PrimaryKey
    val competitionId: String,
    val name: String,
    val icon: Int,
    val color: Int,
    val mode: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class CompetitionTeamRelation(
    @Embedded val competition: CompetitionDb,
    @Relation(
        parentColumn = "competitionId",
        entityColumn = "teamId",
        associateBy = Junction(CompetitionTeamCrossRefDb::class)
    )
    val teams: List<TeamDb>
)

data class CompetitionRoundRelation(
    @Embedded val competition: CompetitionDb,
    @Relation(
        parentColumn = "competitionId",
        entityColumn = "roundId",
        associateBy = Junction(CompetitionRoundCrossRefDb::class)
    )
    val rounds: List<RoundDb>
)