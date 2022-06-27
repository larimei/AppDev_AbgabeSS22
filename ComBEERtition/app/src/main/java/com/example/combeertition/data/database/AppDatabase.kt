package com.example.combeertition.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.combeertition.data.database.competition.CompetitionDao
import com.example.combeertition.data.database.competition.CompetitionDb
import com.example.combeertition.data.database.player.PlayerDao
import com.example.combeertition.data.database.player.PlayerDb
import com.example.combeertition.data.database.relations.competitionteam.CompetitionTeamDao
import com.example.combeertition.data.database.relations.competitionteam.CompetitionTeamDb
import com.example.combeertition.data.database.relations.teamplayer.TeamPlayerDao
import com.example.combeertition.data.database.relations.teamplayer.TeamPlayerDb
import com.example.combeertition.data.database.round.RoundDao
import com.example.combeertition.data.database.round.RoundDb
import com.example.combeertition.data.database.team.TeamDao
import com.example.combeertition.data.database.team.TeamDb

@Database(
    version = 5,
    entities = [
        PlayerDb::class,
        TeamDb::class,
        CompetitionDb::class,
        RoundDb::class,
        TeamPlayerDb::class,
        CompetitionTeamDb::class
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun teamDao(): TeamDao
    abstract fun competitionDao(): CompetitionDao
    abstract fun roundDao(): RoundDao
    abstract fun teamPlayerDao(): TeamPlayerDao
    abstract fun competitionTeamDao(): CompetitionTeamDao
}
