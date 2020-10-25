package org.gdemirkol.misterx.service


import kotlinx.serialization.json.Json
import org.gdemirkol.misterx.model.BoardState
import org.gdemirkol.misterx.model.board.BoardMap
import org.gdemirkol.misterx.model.config.JsonBoardMap
import org.gdemirkol.misterx.model.config.JsonInitialState

class MapManager {

    val boardMap: BoardMap
    val boardState: BoardState

    init {
        val boardMapJsonString = MapManager::class.java.getResource("/board-map.json").readText()
        val initialStateJsonString = MapManager::class.java.getResource("/initial-state.json").readText()

        //https://www.rockandnull.com/kotlin-json/
        val jsonBoardMap = Json.decodeFromString(JsonBoardMap.serializer(), boardMapJsonString)
        val jsonInitialState = Json.decodeFromString(JsonInitialState.serializer(), initialStateJsonString)

        boardMap = jsonBoardMap.convert()
        boardState = jsonInitialState.convert()
    }
}