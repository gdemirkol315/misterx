package org.gdemirkol.misterx.service

import org.gdemirkol.misterx.model.BoardMap
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.gdemirkol.misterx.model.JsonBoardMap
import org.gdemirkol.misterx.model.JsonDataPoint
import javax.xml.crypto.Data

class MapManager {
    fun loadMapConfig() {
        val fileContent = MapManager::class.java.getResource("/mapconfig.json").readText()

        //https://www.rockandnull.com/kotlin-json/
        val dataList = Json.decodeFromString<JsonBoardMap>(fileContent)
        dataList.jsonDataPoints.forEach {
            println(it)
        }
    }
}