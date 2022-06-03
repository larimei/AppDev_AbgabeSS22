package com.example.combeertition.data.database.team

import androidx.room.*
import com.example.combeertition.data.database.player.PlayerDb
import com.example.combeertition.data.database.relations.PlayerTeamCrossRefDb
import java.time.ZonedDateTime

@Entity(tableName = "team")
data class TeamDb(
    @PrimaryKey
    val teamId: String,
    val name: String,
    val icon: Int,
    val color: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class TeamPlayerRelation(
    @Embedded val team: TeamDb,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "playerId",
        associateBy = Junction(PlayerTeamCrossRefDb::class)
    )
    val players: List<PlayerDb>
)