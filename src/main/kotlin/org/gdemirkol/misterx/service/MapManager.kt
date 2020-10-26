package org.gdemirkol.misterx.service


import kotlinx.serialization.json.Json
import org.gdemirkol.misterx.model.BoardState
import org.gdemirkol.misterx.model.StationState
import org.gdemirkol.misterx.model.board.BoardMap
import org.gdemirkol.misterx.model.config.JsonBoardMap
import org.gdemirkol.misterx.model.config.JsonInitialState

class MapManager {

    val boardMap: BoardMap
    val boardState: BoardState
    val assumedStationStates: MutableList<StationState>

    init {
        val boardMapJsonString = MapManager::class.java.getResource("/board-map.json").readText()
        val initialStateJsonString = MapManager::class.java.getResource("/initial-state.json").readText()

        //https://www.rockandnull.com/kotlin-json/
        val jsonBoardMap = Json.decodeFromString(JsonBoardMap.serializer(), boardMapJsonString)
        val jsonInitialState = Json.decodeFromString(JsonInitialState.serializer(), initialStateJsonString)

        boardMap = jsonBoardMap.convert()
        boardState = jsonInitialState.convert(boardMap)
        assumedStationStates = boardState.stationStates.toMutableList()
    }

    fun getAllStationStates(stationStates: List<StationState>, noOfRounds: Int) {
        val newStationStates = stationStates.toMutableList()
        if (noOfRounds == 0)
            return

        stationStates.forEach() {
            it.getNextStationStates(boardMap).forEach() {
                if (!newStationStates.contains(it))
                    newStationStates.add(it)
                if (!assumedStationStates.contains(it))
                    assumedStationStates.add(it)

            }
        }
        getAllStationStates(newStationStates, noOfRounds - 1)

    }
}