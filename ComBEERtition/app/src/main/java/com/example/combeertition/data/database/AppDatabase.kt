package com.example.combeertition.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.combeertition.data.database.player.PlayerDAO
import com.example.combeertition.data.database.player.PlayerDB
import com.example.combeertition.data.database.player_team_cross.PlayerTeamCrossRefDb
import com.example.combeertition.data.database.team.TeamDao
import com.example.combeertition.data.database.team.TeamDb

@Database(
    version = 4,
    entities = [
        PlayerDB::class,
        TeamDb::class,
        PlayerTeamCrossRefDb::class
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDAO
    abstract fun teamDao(): TeamDao
}
