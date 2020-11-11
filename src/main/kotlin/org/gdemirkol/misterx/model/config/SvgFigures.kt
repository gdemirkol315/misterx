
import org.gdemirkol.misterx.model.board.TransportationType
import org.gdemirkol.misterx.service.convertStationsToColors
import java.io.File

fun drawCircle(transportations:List<TransportationType>) {
    val file = File("circle.svg")
    val bufferedReader = file.bufferedReader()
    val text:List<String> = bufferedReader.readLines()
    var i:Int = 0;
    var newSvgContent: String = ""
    val colors = convertStationsToColors(transportations)

    for(line in text){
        if (i == colors.size) i = 0
        newSvgContent += line.replace("?", colors[i])
        i++
    }

    file.writeText(newSvgContent)

}
