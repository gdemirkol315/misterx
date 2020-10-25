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
    fun getAllStationStates(boardMap: BoardMap, stationStates: List<StationState>, noOfRounds: Int): List<StationState> {
        val localStationStates = stationStates.toMutableList()
        if (noOfRounds == 0)
            return localStationStates

        localStationStates.forEach() {
            it.getNextStationStates(boardMap).forEach(
                    localStationStates.add(it)
            )
        }

        getAllStationStates(boardMap,localStationStates,noOfRounds-1)
        return localStationStates
    }
}


