package org.gdemirkol.misterx.service


import kotlinx.serialization.json.Json
import org.gdemirkol.misterx.model.BoardMap
import org.gdemirkol.misterx.model.JsonBoardMap


class MapManager {
    fun loadMapConfig(): BoardMap {
        val fileContent = MapManager::class.java.getResource("/mapconfig.json").readText()
        return Json.decodeFromString(JsonBoardMap.serializer(), fileContent).convert();
    }

}