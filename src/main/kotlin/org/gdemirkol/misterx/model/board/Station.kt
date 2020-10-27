package org.gdemirkol.misterx.model.board

import org.gdemirkol.misterx.model.MapPosition

data class Station(val stationId: Int, val connections: List<Connection>, val stationPosition: MapPosition) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Station

        if (stationId != other.stationId) return false

        return true
    }

    override fun hashCode(): Int {
        return stationId
    }
}

