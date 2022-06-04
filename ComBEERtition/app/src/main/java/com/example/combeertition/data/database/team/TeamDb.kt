package com.example.combeertition.data.database.team

import androidx.room.*
import com.example.combeertition.data.database.player.PlayerDb
import com.example.combeertition.data.database.relations.teamplayer.TeamPlayerDb
import java.time.ZonedDateTime

@Entity(tableName = "team")
data class TeamDb(
    @PrimaryKey
    val teamId: String,
    val name: String,
    val icon: Int,
    val color: Int,
    val wins: Int,
    val looses: Int,
    val matches: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class TeamWithPlayers(
    @Embedded val team: TeamDb,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "playerId",
        associateBy = Junction(TeamPlayerDb::class)
    )
    val players: List<PlayerDb>
)