import com.github.nwillc.ksvg.elements.SVG
import org.gdemirkol.misterx.model.board.BoardMap
import org.gdemirkol.misterx.model.board.Station
import org.gdemirkol.misterx.model.board.TransportationType

private const val VIEWPORT_WIDTH = 3000
private const val VIEWPORT_HEIGHT = 2250

fun Station.svg(viewportOffsetX:Int, viewportOffsetY:Int): SVG {
    val station = this
    val transportationTypes: List<TransportationType> =
            this
                    .connections
                    .map { it.transportationType }

    return SVG.svg(true) {
        this.viewBox = "$viewportOffsetX $viewportOffsetY $VIEWPORT_WIDTH $VIEWPORT_HEIGHT"
        g {
            //attributes["transform"] = "scale(0.5)"
            // Label

            path {
                cssClass = if (transportationTypes.contains(TransportationType.BUS)) "bus" else "taxi"
                id = "LowerHalf"
                d = "M0 25 A12.5 12.5 0 0 0 50 25"
            }

            path {
                cssClass = "taxi"
                id = "UpperHalf"
                d = "M0 25 A12.5 12.5 0 1 1 50 25"
            }

            rect {
                cssClass = if (transportationTypes.contains(TransportationType.METRO)) "metro" else "no-metro"
                id = "rectangle"
                x = "5"
                y = "12.5"
                width = "40"
                height = "25"
            }

            text {
                cssClass = if (transportationTypes.contains(TransportationType.METRO)) "metro-text" else "no-metro-text"
                x = "25"
                y = "30"
                body = station.stationId.toString()
                fontFamily = "monospace"
                this.attributes["font-weight"] = "bold"
                this.attributes["text-anchor"] = "middle"
                fontSize = "17.5"
            }

        }
    }
}


fun BoardMap.svg(): SVG {
    val stations = this.stations
    return SVG.svg(true) {
        height = VIEWPORT_HEIGHT.toString()
        width = VIEWPORT_WIDTH.toString()
        attributes["xmlns"] = "http://www.w3.org/2000/svg"
        style {
            body = """
                 svg .taxi { stroke: black; fill: #be9226}
                 svg .bus { stroke: black; fill: #235555}
                 svg .metro { stroke: black; fill: #ac391a}
                 svg .no-metro { stroke: black; fill: #c5beb0; }
                 svg .metro-text { stroke: grey; fill: #c5beb0}
                 svg .no-metro-text { stroke: black; fill: #282311; }
             """.trimIndent()
        }
        stations.forEach {
            this.children.add(it.svg(-it.stationPosition.x+25,-it.stationPosition.y+25))
        }
    }

}
