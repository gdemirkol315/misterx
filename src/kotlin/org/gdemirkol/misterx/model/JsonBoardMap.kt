package org.gdemirkol.misterx.model

import kotlinx.serialization.Serializable

@Serializable
data class JsonBoardMap (val jsonDataPoints: List<JsonDataPoint>)