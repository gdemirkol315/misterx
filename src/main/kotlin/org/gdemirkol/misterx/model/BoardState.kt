package org.gdemirkol.misterx.model

import org.gdemirkol.misterx.model.board.Player

data class BoardState(val players: List<Player>, val stationStates: List<StationState>, val round: Int = 0)