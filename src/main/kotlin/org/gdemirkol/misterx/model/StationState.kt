package org.gdemirkol.misterx.model

class StationState(stationId: Int,
                   connections: List<Connection>,
                   val playerId: Int) : Station(stationId, connections) {
    fun getNextStationStates(boardMap: BoardMap): List<StationState> =
            boardMap
                    .mapStateLookup.getValue(stationId)
                    .connections
                    .map {
                        StationState(
                                stationId = it.targetStationId,
                                connections = boardMap.mapStateLookup.getValue(it.targetStationId).connections,
                                playerId = playerId)
                    }
}