package org.gdemirkol.misterx.service

import org.gdemirkol.misterx.model.BoardState
import org.gdemirkol.misterx.model.MapPosition
import org.gdemirkol.misterx.model.StationState
import org.gdemirkol.misterx.model.board.*
import org.gdemirkol.misterx.model.config.JsonBoardMap
import org.gdemirkol.misterx.model.config.JsonInitialState

fun JsonBoardMap.convert(): BoardMap {
    val stationLocationLookup = this
            .stations
            .map { it.stationId to MapPosition(it.locationX, it.locationY) }
            .toMap()

    return BoardMap(
            stations = this.connections
                    .flatMap { jsonDataPoint -> //wie versteht er das jsonDataPoint JsonConnection ist?
                        listOf(
                                Station(
                                        stationId = jsonDataPoint.sourceStationId,
                                        connections = listOf(
                                                Connection(
                                                        transportationType = TransportationType.valueOf(jsonDataPoint.transportationType),
                                                        targetStationId = jsonDataPoint.targetStationId)
                                        ),
                                        stationPosition = stationLocationLookup.getOrDefault(jsonDataPoint.sourceStationId, MapPosition(0, 0))
                                ),
                                Station(
                                        stationId = jsonDataPoint.targetStationId,
                                        connections = listOf(
                                                Connection(
                                                        transportationType = TransportationType.valueOf(jsonDataPoint.transportationType),
                                                        targetStationId = jsonDataPoint.sourceStationId)
                                        ),
                                        stationPosition = stationLocationLookup.getOrDefault(jsonDataPoint.targetStationId, MapPosition(0, 0))

                                )
                        )
                    }
                    .groupBy { it.stationId }
                    .map { it.value.reduce { acc, station -> Station(it.key, listOf(acc.connections, station.connections).flatten(), acc.stationPosition) } }
    )
}

fun JsonInitialState.convert(boardMap: BoardMap): BoardState {
    val players = this
            .playerPositions
            .map {
                when (it.playerType) {
                    PlayerType.MISTERX -> MisterX(playerId = it.playerId)
                    PlayerType.BOBBY -> Bobby(playerId = it.playerId)
                    PlayerType.DETECTIVE -> Detective(playerId = it.playerId)
                }
            }

    val stationStates = this
            .playerPositions
            .map { jsonPlayerPosition ->
                StationState(
                        station = boardMap.mapStateLookup.getValue(jsonPlayerPosition.stationId),
                        player = players.first { it.playerId == jsonPlayerPosition.playerId }
                )
            }

    return BoardState(players, stationStates)
}