package com.example.combeertition.domain.team

import com.example.combeertition.data.teamRepository

class GetTeamsUseCase {
    suspend operator fun invoke() = teamRepository.getAllTeams()
}