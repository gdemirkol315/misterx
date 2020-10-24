package org.gdemirkol.misterx.service

import org.gdemirkol.misterx.model.*

fun JsonBoardMap.convert() = BoardMap(
        stations = this.jsonDataPoints
                .flatMap { jsonDataPoint ->
                    listOf(
                            Station(
                                    stationId = jsonDataPoint.sourceStationId,
                                    connections = listOf(
                                            Connection(
                                                    transportationType = TransportationType.valueOf(jsonDataPoint.transportationType),
                                                    targetStationId = jsonDataPoint.targetStationId)
                                    )
                            ),
                            Station(
                                    stationId = jsonDataPoint.targetStationId,
                                    connections = listOf(
                                            Connection(
                                                    transportationType = TransportationType.valueOf(jsonDataPoint.transportationType),
                                                    targetStationId = jsonDataPoint.sourceStationId)
                                    )
                            )
                    )
                }
                .groupBy { it.stationId }
                .map { it.value.reduce { acc, station -> Station(it.key, listOf(acc.connections, station.connections).flatten()) } }
)
