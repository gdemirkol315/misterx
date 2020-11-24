package org.gdemirkol.misterx.model

import org.gdemirkol.misterx.model.board.BoardMap
import org.gdemirkol.misterx.model.board.Player
import org.gdemirkol.misterx.model.board.Station
import org.gdemirkol.misterx.model.board.TransportationType

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
    fun getNextStationStates(boardMap: BoardMap, transportationTypes: List<TransportationType>): List<StationState> =
            //TODO: implement filtering stations with transportation types that have been used
            station
                    .connections
                    .filter { player.canMove(it.transportationType) }
                    .map {
                        StationState(
                                station=  boardMap.mapStateLookup.getValue(it.targetStationId),
                                player = player.move(it.transportationType))
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

