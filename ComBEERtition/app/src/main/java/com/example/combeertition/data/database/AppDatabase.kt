package com.example.combeertition.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.combeertition.data.database.competition.CompetitionDao
import com.example.combeertition.data.database.competition.CompetitionDb
import com.example.combeertition.data.database.player.PlayerDao
import com.example.combeertition.data.database.player.PlayerDb
import com.example.combeertition.data.database.relations.CompetitionRoundCrossRefDb
import com.example.combeertition.data.database.relations.CompetitionTeamCrossRefDb
import com.example.combeertition.data.database.relations.PlayerTeamCrossRefDb
import com.example.combeertition.data.database.round.RoundDao
import com.example.combeertition.data.database.round.RoundDb
import com.example.combeertition.data.database.team.TeamDao
import com.example.combeertition.data.database.team.TeamDb

@Database(
    version = 6,
    entities = [
        PlayerDb::class,
        TeamDb::class,
        PlayerTeamCrossRefDb::class,
        CompetitionRoundCrossRefDb::class,
        CompetitionTeamCrossRefDb::class,
        CompetitionDb::class,
        RoundDb::class
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun teamDao(): TeamDao
    abstract fun competitionDao(): CompetitionDao
    abstract fun roundDao(): RoundDao
}
