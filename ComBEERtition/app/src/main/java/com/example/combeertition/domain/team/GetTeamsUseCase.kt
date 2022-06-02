package com.example.combeertition.domain.team

import com.example.combeertition.data.teamRepository

class GetTeamsUseCase {
    operator fun invoke() = teamRepository.getAllTeams()
}