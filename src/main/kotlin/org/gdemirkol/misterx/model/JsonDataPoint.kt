package org.gdemirkol.misterx.model

import kotlinx.serialization.Serializable


@Serializable
data class JsonDataPoint(val sourceStationId: Int, val transportationType: String, val targetStationId: Int)