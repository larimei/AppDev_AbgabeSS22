package com.example.combeertition.domain.team

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateTeamUseCase {
    suspend operator fun invoke(
        teamId: TeamId,
        name: String,
        color: Color,
        players: List<String>
    ) = withContext(Dispatchers.Default)
    {
        teamRepository.updateTeam(Team.create(teamId, name, R.drawable.ic_player, color, players))
    }

}