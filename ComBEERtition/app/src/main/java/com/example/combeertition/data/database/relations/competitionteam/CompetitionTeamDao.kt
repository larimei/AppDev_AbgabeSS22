package com.example.combeertition.data.database.relations.competitionteam

import androidx.room.*
import com.example.combeertition.data.database.player.PlayerDb

@Dao
interface CompetitionTeamDao {

    @Insert
    suspend fun insert(item: CompetitionTeamDb)

    @Delete
    suspend fun delete(item: CompetitionTeamDb)

    @Query("SELECT * FROM competitionTeam")
    suspend fun getAll(): List<CompetitionTeamDb>

    @Query("SELECT * FROM competitionTeam WHERE competitionTeamId = :id")
    suspend fun getById(id: String): CompetitionTeamDb?

    @Query("SELECT * FROM competitionTeam WHERE competitionId = :id")
    suspend fun getByCompetitionId(id: String): List<CompetitionTeamDb>

    @Query("SELECT * FROM competitionTeam WHERE teamId = :id")
    suspend fun getByTeamId(id: String): List<CompetitionTeamDb>
}