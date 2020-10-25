package org.gdemirkol.misterx

import org.gdemirkol.misterx.model.StationState
import org.gdemirkol.misterx.service.MapManager

fun main(args: Array<String>) {
    val mapManager = MapManager()
    val boardMap =mapManager.loadMapConfig()
    val stationState = StationState(
            stationId = boardMap.stations.get(0).stationId,
            connections = boardMap.stations.get(0).connections,
            playerId = 1)
    val stationStates = stationState.getAllStationStates(boardMap,stationState.getNextStationStates(boardMap),3)

}
