package org.gdemirkol.misterx.model.config

import kotlinx.serialization.Serializable
import org.gdemirkol.misterx.model.board.PlayerType

@Serializable
data class JsonInitialState(val playerPositions: List<JsonPlayerPosition>)

@Serializable
data class JsonPlayerPosition(val stationId: Int, val playerId: Int, val playerType: PlayerType)