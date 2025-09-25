package org.example.monde

import org.example.dresseur.Entraineur
import org.example.monstre.EspeceMonstre
import org.jetbrains.annotations.Nullable

class Zone(
    var id : Int,
    var nom : String,
    var expZone : Int,
    var especesMonstres : MutableList <EspeceMonstre> = mutableListOf<EspeceMonstre>(),
    var zoneSuivante : Zone?,
    var zonePrecedante : Zone?,


    //TODO rencontreMonstre()

) {

    fun genereMonstre() {
        var id: Int
        var espece: String
        var nom: String
        var entraineur: Entraineur?
        var expInit: Double


    }

}