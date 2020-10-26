package org.gdemirkol.misterx.model.config

import kotlinx.serialization.Serializable

@Serializable
data class JsonBoardMap(val stations: List<JsonStation>, val connections: List<JsonConnection>)

@Serializable
data class JsonStation(val stationId: Int, val locationX: Int, val locationY: Int)

@Serializable
data class JsonConnection(val sourceStationId: Int, val transportationType: String, val targetStationId: Int)