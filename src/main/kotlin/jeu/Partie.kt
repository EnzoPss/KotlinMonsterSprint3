package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.especeSpringleaf
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

class Partie(
    val id: Int,
    var joueur: Entraineur,
    var zone: Zone
) {

    fun choixStarter() {
        var starter1 = IndividuMonstre(1, "springleaf", especeSpringleaf, null, 1500.0)
        var starter2 = IndividuMonstre(2, "flamkip", especeSpringleaf, null, 1500.0)
        var starter3 = IndividuMonstre(3, "aquamy", especeSpringleaf, null, 1500.0)
        var starter: IndividuMonstre? = null

        starter1.afficheDetail()
        starter2.afficheDetail()
        starter3.afficheDetail()

        println("1. Springleaf    2. Flamkip    3.Aquamy")
        var choixStarter = readln().toInt()

        if (choixStarter !in 1..3) choixStarter()
        if (choixStarter == 1) {starter = starter1}
        else if (choixStarter == 2) {starter = starter2}
        else if (choixStarter == 3) {starter = starter3}

        starter!!.renommer()

        joueur.equipeMonstre.add(starter)
        starter.entraineur = joueur

    }

    fun modifierOrdreEquipe() {



    }


}