package org.gdemirkol.misterx

import org.gdemirkol.misterx.service.MapManager

fun main(args: Array<String>) {
    val mapManager = MapManager()
    println("Board - Stations")
    mapManager.boardMap.stations.forEach { println(it) }
    println()
    println()
    println("Initial Board State - Players")
    mapManager.boardState.players.forEach { println(it) }
    println()
    println("Initial Board State - Station Sates")
    mapManager.boardState.stationStates.forEach { println(it) }
    println()
    println()
    println("Board State - Station Sates - After 3 rounds")
    mapManager.getAllStationStates(mapManager.boardState.stationStates,3).forEach { println(it) }

}
