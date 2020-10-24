package org.gdemirkol.misterx.service


import kotlinx.serialization.json.Json
import org.gdemirkol.misterx.model.JsonBoardMap

class MapManager {
    fun loadMapConfig() {
        val fileContent = MapManager::class.java.getResource("/mapconfig.json").readText()

        //https://www.rockandnull.com/kotlin-json/
        val jsonBoardMap = Json.decodeFromString(JsonBoardMap.serializer(), fileContent)

        jsonBoardMap.convert().stations.forEach {
            println(it)
        }
    }
}