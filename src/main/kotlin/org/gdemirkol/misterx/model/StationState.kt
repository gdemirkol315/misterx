package org.gdemirkol.misterx.model

import org.gdemirkol.misterx.model.board.*

data class StationState(val station: Station,
                        val player: Player) {
    fun getNextStationStates(boardMap: BoardMap): List<StationState> =
            station
                    .connections
                    .filter { player.canMove(it.transportationType) }
                    .map {
                        StationState(
                                station = boardMap.mapStateLookup.getValue(it.targetStationId),
                                player = player.move(it.transportationType))
                    }
    //TODO: do cool Stefan stuff with map&filter
    fun getNextPossibleStations(boardMap: BoardMap, transportationType: TransportationType): List<Station> {

        var connections: List<Connection> = station.connections
        lateinit var stations: MutableList<Station>
        lateinit var possibleStationIds: MutableList<Int>

        for (i in 1..connections.size) {
            if (connections[i].transportationType==transportationType)
                possibleStationIds.add(connections[i].targetStationId)
        }

        for (i in 1..possibleStationIds.size)
            stations.add(boardMap.mapStateLookup.getValue(possibleStationIds[i]))

        return stations;

    }

    fun getNextPossibleStations(boardMap: BoardMap, transportationTypes: List<TransportationType>): List<Station> {
        lateinit var stations: MutableList<Station>
        for (i in 1..transportationTypes.size)
            stations.addAll(getNextPossibleStations(boardMap,transportationTypes[i]))
        return stations
    }


    fun getPossibleNextStates(boardMap: BoardMap, numberOfRounds: Int): List<StationState> {
        return if (numberOfRounds == 0) {
            emptyList()
        } else {
            val nextStates = this.getNextStationStates(boardMap)
            if (numberOfRounds > 1) {
                listOf(
                        nextStates,
                        nextStates.flatMap { it.getPossibleNextStates(boardMap, numberOfRounds - 1) }
                ).flatten()
            } else {
                nextStates
            }
        }
    }

    fun getPossibleNextStates(boardMap: BoardMap, numberOfRounds: Int, transportationTypes: List<TransportationType>, startStation: Station): List<StationState> {
        return if (numberOfRounds == 0) {
            emptyList()
        } else {
            val nextStates = this.getNextStationStates(boardMap)
            if (numberOfRounds > 1) {
                listOf(
                        nextStates,
                        nextStates.flatMap { it.getPossibleNextStates(boardMap, numberOfRounds - 1) }
                ).flatten()
            } else {
                nextStates
            }
        }
    }

}

