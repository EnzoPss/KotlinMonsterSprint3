package org.example.monde

import org.example.dresseur.Entraineur
import org.example.jeu.CombatMonstre
import org.example.joueur
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import org.jetbrains.annotations.Nullable
import kotlin.random.Random

class Zone(
    var id : Int,
    var nom : String,
    var expZone : Int,
    var especesMonstres : MutableList <EspeceMonstre> = mutableListOf<EspeceMonstre>(),
    var zoneSuivante : Zone?,
    var zonePrecedante : Zone?

) {

    fun genereMonstre(): IndividuMonstre {
        var especeAlea = this.especesMonstres.random()
        var nouveauMonstre = IndividuMonstre(Random.nextInt(4,151), especeAlea.nom, especeAlea, null, expZone * Random.nextDouble(0.8,1.2))

        return nouveauMonstre
    }

    fun rencontreMonstre() {
        var monstreSauvage = genereMonstre()
        var premierMonstre: IndividuMonstre? = null
        for (monstre in joueur.equipeMonstre) {
            if (monstre.pv > 0) {premierMonstre = monstre}
        }
        var combat = CombatMonstre(premierMonstre!!, monstreSauvage)
        combat.lanceCombat()
    }

}