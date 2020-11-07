import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.SVG

import java.io.FileWriter


fun drawCircle() {
    val svg = SVG.svg(true) {
        height = "300"
        width = "300"
        style {
            body = """

                 svg .black-stroke { stroke: black; stroke-width: 2; }
                 svg .fur-color { fill: white; }

             """.trimIndent()
        }
        // Label

        circle {
            cssClass = "black-stroke"
            id = "face"
            cx = "180"
            cy = "140"
            r = "80"
            fill = "#aaa70f"
        }
    }

    FileWriter("circle.svg").use {
        svg.render(it, RenderMode.FILE)
    }
}
