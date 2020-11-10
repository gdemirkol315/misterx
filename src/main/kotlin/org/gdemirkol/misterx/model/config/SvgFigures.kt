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
    /*
    <svg width="550" height="800" xmlns="http://www.w3.org/2000/svg">
        <path d="M100 200 A50 50 0 0 0 200 200" stroke="black" fill="yellow"/>
        <path d="M100 200 A50 50 0 1 1 200 200" stroke="black" fill="blue"/>
        <path d="M100 200 A100 100 0 0 0 200 200" stroke="black" fill="red"/>
    </svg>
    */
    FileWriter("circle.svg").use {
        svg.render(it, RenderMode.FILE)
    }
}
