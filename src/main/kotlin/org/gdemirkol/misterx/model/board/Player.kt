package org.gdemirkol.misterx.model.board

sealed class Player(open val playerId: Int,
                    open val busTickets: Int,
                    open val taxiTickets: Int,
                    open val metroTickets: Int,
                    open val blackTickets: Int) {

    abstract fun copy(playerId: Int = this.playerId,
                      busTickets: Int = this.busTickets,
                      taxiTickets: Int = this.taxiTickets,
                      metroTickets: Int = this.metroTickets,
                      blackTickets: Int = this.blackTickets): Player

    fun canMove(transportationType: TransportationType): Boolean = when (transportationType) {
        TransportationType.BUS -> (busTickets > 0 || blackTickets > 0)
        TransportationType.TAXI -> (taxiTickets > 0 || blackTickets > 0)
        TransportationType.METRO -> (metroTickets > 0 || blackTickets > 0)
        TransportationType.FERRY -> (blackTickets > 0)
    }



    fun move(transportationType: TransportationType): Player = when (transportationType) {
        TransportationType.BUS -> this.copy(busTickets = this.busTickets - 1)
        TransportationType.TAXI -> this.copy(taxiTickets = this.taxiTickets - 1)
        TransportationType.METRO -> this.copy(metroTickets = this.metroTickets - 1)
        TransportationType.FERRY -> this.copy(blackTickets = this.blackTickets - 1)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Player && other.playerId == playerId) return true
        return false
    }

    override fun hashCode(): Int {
        return playerId
    }

    override fun toString(): String {
        return "Player(playerId=$playerId, busTickets=$busTickets, taxiTickets=$taxiTickets, metroTickets=$metroTickets, blackTickets=$blackTickets)"
    }

}

class Detective(override val playerId: Int,
                override val busTickets: Int = 8,
                override val taxiTickets: Int = 11,
                override val metroTickets: Int = 4,
                override val blackTickets: Int = 0
) : Player(playerId = playerId,
        busTickets = busTickets,
        taxiTickets = taxiTickets,
        metroTickets = metroTickets,
        blackTickets = blackTickets) {
    override fun copy(playerId: Int, busTickets: Int, taxiTickets: Int, metroTickets: Int, blackTickets: Int) =
            Detective(
                    playerId = playerId,
                    busTickets = busTickets,
                    taxiTickets = taxiTickets,
                    metroTickets = metroTickets,
                    blackTickets = blackTickets
            )

    override fun toString(): String {
        return "Detective(playerId=$playerId, busTickets=$busTickets, taxiTickets=$taxiTickets, metroTickets=$metroTickets, blackTickets=$blackTickets)"
    }

}

class Bobby(override val playerId: Int,
            override val busTickets: Int = Int.MAX_VALUE,
            override val taxiTickets: Int = Int.MAX_VALUE,
            override val metroTickets: Int = Int.MAX_VALUE,
            override val blackTickets: Int = 0
) : Player(playerId = playerId,
        busTickets = busTickets,
        taxiTickets = taxiTickets,
        metroTickets = metroTickets,
        blackTickets = blackTickets) {
    override fun copy(playerId: Int, busTickets: Int, taxiTickets: Int, metroTickets: Int, blackTickets: Int) =
            Bobby(
                    playerId = playerId,
                    busTickets = busTickets,
                    taxiTickets = taxiTickets,
                    metroTickets = metroTickets,
                    blackTickets = blackTickets
            )

    override fun toString(): String {
        return "Bobby(playerId=$playerId, busTickets=$busTickets, taxiTickets=$taxiTickets, metroTickets=$metroTickets, blackTickets=$blackTickets)"
    }
}

class MisterX(override val playerId: Int = 0,
              override val busTickets: Int = Int.MAX_VALUE,
              override val taxiTickets: Int = Int.MAX_VALUE,
              override val metroTickets: Int = Int.MAX_VALUE,
              override val blackTickets: Int = 2
) : Player(playerId = playerId,
        busTickets = busTickets,
        taxiTickets = taxiTickets,
        metroTickets = metroTickets,
        blackTickets = blackTickets) {
    override fun copy(playerId: Int, busTickets: Int, taxiTickets: Int, metroTickets: Int, blackTickets: Int) =
            MisterX(
                    playerId = playerId,
                    busTickets = busTickets,
                    taxiTickets = taxiTickets,
                    metroTickets = metroTickets,
                    blackTickets = blackTickets
            )

    override fun toString(): String {
        return "MisterX(playerId=$playerId, busTickets=$busTickets, taxiTickets=$taxiTickets, metroTickets=$metroTickets, blackTickets=$blackTickets)"
    }

}