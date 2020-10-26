package org.gdemirkol.misterx

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.gdemirkol.misterx.service.MapManager

fun main(args: Array<String>) {
    val mapManager = MapManager()

    println("open localhost:8088")

    embeddedServer(Netty, 8088) {
        routing {
            get("/") {
                call.respondTextWriter(contentType = ContentType("text", "html")) {
                    this.appendHTML().html {
                        body {
                            div {
                                h1 { +"Initial Board State - Players" }
                                ul {
                                    mapManager.boardState.players.forEach { player ->
                                        li { text("${player.playerId}: ${player.javaClass}".escapeHTML()) }
                                    }
                                }

                                h1 { +"Board - Stations" }
                                ul {
                                    mapManager.boardMap.stations.forEach { station ->
                                        li { text("${station.stationId}: ${station.connections}".escapeHTML()) }
                                    }
                                }

                                h1 { +"Initial Board State - Station States" }
                                ul {
                                    mapManager.boardState.stationStates.forEach { stationState ->
                                        li { text("${stationState.station.stationId}: ${stationState.player.playerId}".escapeHTML()) }
                                    }
                                }

                                h1 { +"Board State - Station Sates - After 3 rounds" }
                                ul {
                                    mapManager.getPossibleNextStates(3).forEach { stationState ->
                                        li { text("${stationState.station.stationId}: ${stationState.player.playerId}".escapeHTML()) }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }.start(wait = true)
}
