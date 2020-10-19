package org.gdemirkol.misterx.service

import org.gdemirkol.misterx.model.BoardMap
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class MapManager {
    fun loadMapConfig() {
        val fileContent = MapManager::class.java.getResource("/mapconfig.json").readText()
        println(fileContent)
        val jsonData = Json.parse
        println(jsonData)
    }
}