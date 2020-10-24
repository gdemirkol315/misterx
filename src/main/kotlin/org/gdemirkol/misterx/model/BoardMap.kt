package org.gdemirkol.misterx.model

data class BoardMap(val stations: List<Station>){
    val mapStateLookup = stations.map { it.stationId to it }.toMap()
}