package org.gdemirkol.misterx.service

import org.gdemirkol.misterx.model.BoardMap
import org.gdemirkol.misterx.model.Connection
import org.gdemirkol.misterx.model.JsonBoardMap
import org.gdemirkol.misterx.model.Station

fun JsonBoardMap.convert():BoardMap{
    this.jsonDataPoints.map { Station(it.sourceStationId, listOf(Connection())) }
}