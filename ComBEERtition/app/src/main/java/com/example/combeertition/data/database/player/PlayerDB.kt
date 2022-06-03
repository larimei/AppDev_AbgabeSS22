package com.example.combeertition.data.database.player

import androidx.room.*
import java.time.ZonedDateTime

@Entity(tableName = "player")
data class PlayerDB(
    @PrimaryKey
    val playerId: String,
    val name: String,
    val icon: Int,
    val color: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime
)

/*data class PlayerTeamRelation(
    @Embedded val player: PlayerDB,
    @Relation(
        parentColumn = "playerId",
        entityColumn = "teamId",
        associateBy = Junction(PlayerTeamCrossRefDb::class)
    )
    val teams: List<TeamDb>
)*/