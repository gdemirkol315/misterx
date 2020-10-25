package org.gdemirkol.misterx.model

import org.gdemirkol.misterx.model.board.BoardMap
import org.gdemirkol.misterx.model.board.Player

data class StationState(val stationId: Int,
                        val player: Player) {
    fun getNextStationStates(boardMap: BoardMap): List<StationState> =
            boardMap
                    .mapStateLookup.getValue(stationId)
                    .connections
                    .filter { player.canMove(it.transportationType) }
                    .map {
                        StationState(
                                stationId = it.targetStationId,
                                player = player)
                    }
}