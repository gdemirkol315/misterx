package org.gdemirkol.misterx.model.config

import com.github.nwillc.ksvg.RenderMode
import svg
import org.gdemirkol.misterx.service.MapManager
import org.junit.Test
import java.io.FileWriter

internal class SvgFiguresKtTest{

    @Test
    fun drawCircleTaxi (){
        val mapManager= MapManager()
        val circle = mapManager.boardMap.stations.first().svg(0,0,100,100)
        FileWriter("circle.svg").use {
            circle.render(it, RenderMode.FILE)
        }
    }

}
