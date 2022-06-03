package com.example.combeertition.data.database.relations

import androidx.room.Entity

@Entity(primaryKeys = ["competitionId", "teamId"])
data class CompetitionTeamCrossRefDb(
    val competitionId: String,
    val teamId: String

)