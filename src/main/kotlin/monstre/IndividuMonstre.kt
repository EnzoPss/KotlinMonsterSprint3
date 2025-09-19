package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.round


class IndividuMonstre(
    val id: Int,
    val nom: String,
    val espece: EspeceMonstre,
    val entraineur: Entraineur?,

    expInit: Double,
) {

    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + Random.nextInt(-2,2)
    var defense: Int = espece.baseDefense + Random.nextInt(-2,2)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2,2)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2,2)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2,2)
    var pvMax: Int = espece.basePv + Random.nextInt(-5,5)
    var potentiel: Double = Random.nextDouble(0.5,2.0)
    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field=nouveauPv.coerceIn(0,pvMax)
        }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau:Int): Double {
        return (100.0*(niveau-1)).pow(2)
    }

    fun levelUp(){
        attaque = round((attaque*potentiel)).toInt()+Random.nextInt(-2,2)
        defense = round((defense*potentiel)).toInt()+Random.nextInt(-2,2)
        vitesse = round((vitesse*potentiel)).toInt()+Random.nextInt(-2,2)
        attaqueSpe = round((attaqueSpe*potentiel)).toInt()+Random.nextInt(-2,2)
        defenseSpe = round((defenseSpe*potentiel)).toInt()+Random.nextInt(-2,2)
        pvMax = round((pvMax*potentiel)).toInt()+Random.nextInt(-5,5)
    }



    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            var estNiveau1 = (niveau == 1)
            if (field >= palierExp(niveau)) {
                levelUp()
                estNiveau1 = false
            }
            if (estNiveau1 == false) println("Le montre $nom est maintenant niveau $niveau !")
        }

    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }

}