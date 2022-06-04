package com.example.combeertition.domain.team

import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.*
import java.util.*

class DeleteTeamUseCase {
    suspend operator fun invoke(team: Team): Boolean {
        teamRepository.deleteTeam(team)
        return true
    }
}
