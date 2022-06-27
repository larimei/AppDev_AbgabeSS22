package com.example.combeertition.domain.rounds

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.*

class UpdateRoundUseCase {
    suspend operator fun invoke(roundId: RoundId, pointsFirst: Int, pointsSecond: Int): Boolean {
        val updatedRound = roundsRepository.getRoundById(roundId)

        if (updatedRound != null) {
            if (pointsFirst == 0) {
                roundsRepository.updateRound(
                    Round.create(
                        roundId,
                        updatedRound.round,
                        updatedRound.competition,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        updatedRound.secondTeam,
                        updatedRound.firstTeam,
                        pointsFirst,
                        pointsSecond
                    )
                )
                var team = teamRepository.getTeamById(TeamId(updatedRound.firstTeam))

                if (team != null) {
                    teamRepository.updateTeam(
                        Team.create(
                            team.id,
                            team.name,
                            team.icon,
                            team.color,
                            team.players,
                            team.wins,
                            team.looses + 1,
                            team.matches + 1
                        )
                    )

                    for (player in team.players) {
                        var player = playerRepository.getPlayerById(PlayerId(player))
                        if (player != null) {
                            playerRepository.updatePlayer(
                                Player.create(
                                    player.id,
                                    player.name,
                                    player.icon,
                                    player.color,
                                    player.wins,
                                    player.looses + 1,
                                    player.matches + 1
                                )
                            )
                        }
                    }
                }


                team = teamRepository.getTeamById(TeamId(updatedRound.secondTeam))
                if (team != null) {
                    teamRepository.updateTeam(
                        Team.create(
                            team.id,
                            team.name,
                            team.icon,
                            team.color,
                            team.players,
                            team.wins + 1,
                            team.looses,
                            team.matches + 1
                        )
                    )
                    for (player in team.players) {
                        var player = playerRepository.getPlayerById(PlayerId(player))
                        if (player != null) {
                            playerRepository.updatePlayer(
                                Player.create(
                                    player.id,
                                    player.name,
                                    player.icon,
                                    player.color,
                                    player.wins + 1,
                                    player.looses,
                                    player.matches + 1
                                )
                            )
                        }
                    }
                }
            } else if (pointsSecond == 0) {
                roundsRepository.updateRound(
                    Round.create(
                        roundId,
                        updatedRound.round,
                        updatedRound.competition,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        pointsFirst,
                        pointsSecond
                    )
                )
                var team = teamRepository.getTeamById(TeamId(updatedRound.firstTeam))

                if (team != null) {
                    teamRepository.updateTeam(
                        Team.create(
                            team.id,
                            team.name,
                            team.icon,
                            team.color,
                            team.players,
                            team.wins + 1,
                            team.looses,
                            team.matches + 1
                        )
                    )
                    for (player in team.players) {
                        var player = playerRepository.getPlayerById(PlayerId(player))
                        if (player != null) {
                            playerRepository.updatePlayer(
                                Player.create(
                                    player.id,
                                    player.name,
                                    player.icon,
                                    player.color,
                                    player.wins + 1,
                                    player.looses,
                                    player.matches + 1
                                )
                            )
                        }
                    }
                }
                team = teamRepository.getTeamById(TeamId(updatedRound.secondTeam))
                if (team != null) {
                    teamRepository.updateTeam(
                        Team.create(
                            team.id,
                            team.name,
                            team.icon,
                            team.color,
                            team.players,
                            team.wins,
                            team.looses + 1,
                            team.matches + 1
                        )
                    )
                    for (player in team.players) {
                        var player = playerRepository.getPlayerById(PlayerId(player))
                        if (player != null) {
                            playerRepository.updatePlayer(
                                Player.create(
                                    player.id,
                                    player.name,
                                    player.icon,
                                    player.color,
                                    player.wins,
                                    player.looses + 1,
                                    player.matches + 1
                                )
                            )
                        }
                    }
                }
            } else {
                roundsRepository.updateRound(
                    Round.create(
                        roundId,
                        updatedRound.round,
                        updatedRound.competition,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        updatedRound.winner,
                        updatedRound.loser,
                        pointsFirst,
                        pointsSecond
                    )
                )
            }
            return true
        }
        return false
    }
}