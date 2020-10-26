package org.gdemirkol.misterx.model

import org.gdemirkol.misterx.model.board.BoardMap
import org.gdemirkol.misterx.model.board.Player
import org.gdemirkol.misterx.model.board.Station

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


}

