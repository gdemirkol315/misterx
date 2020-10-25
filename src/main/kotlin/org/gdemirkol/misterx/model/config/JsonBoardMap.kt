package org.gdemirkol.misterx.model.config

import kotlinx.serialization.Serializable

@Serializable
data class JsonBoardMap(val connections: List<JsonConnection>)


@Serializable
data class JsonConnection(val sourceStationId: Int, val transportationType: String, val targetStationId: Int)