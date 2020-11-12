package org.gdemirkol.misterx

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.gdemirkol.misterx.service.MapManager
import svg

fun main(args: Array<String>) {
    val mapManager = MapManager()

    println("open localhost:8088")

    embeddedServer(Netty, 8088) {
        routing {
            static {
                resource("/game-map.png", "game-map.png")
            }
            get("/test.svg") {
                call.respondText(text = mapManager.boardMap.svg().toString(), ContentType("image", "svg+xml"))
            }

            get("/") {
                call.respondTextWriter(contentType = ContentType("text", "html")) {
                    this.appendHTML().html {
                        head {
                            style {
                                unsafe {
                                    raw("""
                                           .image-style {
                                              position: absolute;
                                              top: 0;
                                              left: 0;
                                              border: 1px red solid;
                                              width: 1024px;
                                              height: 768px;
                                            }
                                        """)
                                }
                            }
                        }

                        body {
                            div {
                                img {
                                    src = "/game-map.png"
                                    classes = setOf("image-style")
                                }
                                img {
                                    src = "/test.svg"
                                    classes = setOf("image-style")
                                }
                            }
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
