package org.gdemirkol.misterx.model.config

import drawCircle
import org.gdemirkol.misterx.model.board.*
import org.gdemirkol.misterx.service.MapManager
import org.junit.Test

internal class SvgFiguresKtTest{

    @Test
    fun drawCircleTaxi (){
        val mapManager= MapManager()

        val transportationTypes: List<TransportationType> =
                        mapManager
                                .boardMap
                                .stations[0]
                                .connections
                                .map { it.transportationType}
        drawCircle(transportationTypes)
    }

}
