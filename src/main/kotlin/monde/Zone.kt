package org.example.monde

import org.example.monstre.EspeceMonstre
import org.jetbrains.annotations.Nullable

class Zone(
    var id : Int,
    var nom : String,
    var expZone : Int,
    var especesMonstres : MutableList <EspeceMonstre> = mutableListOf<EspeceMonstre>(),
    var zoneSuivante : Zone?,
    var zonePrecedante : Zone?,

    //TODO genereMonstre()
    //TODO rencontreMonstre()

) {

}