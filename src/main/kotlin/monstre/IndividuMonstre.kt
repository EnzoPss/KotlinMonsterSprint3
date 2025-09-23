package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.round


class IndividuMonstre(
    val id: Int,
    var nom: String,
    val espece: EspeceMonstre,
    var entraineur: Entraineur?,

    expInit: Double,
) {

    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + Random.nextInt(-2,2)
        get() = field
        set(value) {
            field = value
            if (field <=0) field = 1
        }
    var defense: Int = espece.baseDefense + Random.nextInt(-2,2)
        get() = field
        set(value) {
            field = value
            if (field <=0) field = 1
        }
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2,2)
        get() = field
        set(value) {
            field = value
            if (field <=0) field = 1
        }
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2,2)
        get() = field
        set(value) {
            field = value
            if (field <=0) field = 1
        }
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2,2)
        get() = field
        set(value) {
            field = value
            if (field <=0) field = 1
        }
    var pvMax: Int = espece.basePv + Random.nextInt(-5,5)
        get() = field
        set(value) {
            field = value
            if (field <=0) field = 1
        }
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
        return 100.0*(niveau-1).toDouble().pow(2)
    }

    fun levelUp(){
        niveau += 1
        attaque += round((this.espece.modAttaque*potentiel)).toInt()+Random.nextInt(-2,2)
        defense += round((this.espece.modDefense*potentiel)).toInt()+Random.nextInt(-2,2)
        vitesse += round((this.espece.modVitesse*potentiel)).toInt()+Random.nextInt(-2,2)
        attaqueSpe += round((this.espece.modAttaqueSpe*potentiel)).toInt()+Random.nextInt(-2,2)
        defenseSpe += round((this.espece.modDefenseSpe*potentiel)).toInt()+Random.nextInt(-2,2)
        pvMax += round((this.espece.modPv*potentiel)).toInt()+Random.nextInt(-5,5)
    }



    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            var estNiveau1 = (niveau == 1)
            while (field >= palierExp(niveau)) {
                levelUp()

            if (estNiveau1 == false) println("Le montre $nom est maintenant niveau $niveau !")
            }
        }

    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }

    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */

    fun attaquer(monstre: IndividuMonstre) {
        var degatBrut = this.attaque
        var degatTotal = degatBrut-(this.defense / 2)

        if (degatTotal < 1) degatTotal =1

        var pvAvant = monstre.pv
        monstre.pv -= degatTotal
        var pvApres = monstre.pv

        println("${nom} inflige ${pvAvant - pvApres} dégats à ${monstre.nom}")

    }


    fun renommer() {
        println("Renommer ${nom}")
        var nouvNom= readln().toString()
        if (nouvNom != "") nom = nouvNom
    }

    fun afficheDetail() {
        print(espece.afficheArt())
        println("============================\n" +
                "Nom : ${nom}    Lvl : ${niveau}\n Exp : ${exp}    Hp : ${pv}/${pvMax}\n" +
                "============================\n" +
                "Atk : ${attaque}    Def : ${defense}    Spd : ${vitesse}\n" +
                "AtkSpe : ${attaqueSpe}    DefSpe : ${defenseSpe}\n" +
                "Potentiel : ${potentiel}\n" +
                "============================"
                )
    }


    override fun toString(): String {
        val textePresentation =
            "- Nom : ${nom}\n - Espece : ${espece.nom}\n - Point de vie : ${pv}\n - Point de vie max : ${pvMax}\n " +
            "- Attaque : ${attaque}\n - Defense : ${defense}\n - Vitesse : ${vitesse}\n " +
            "- AttaqueSpe : ${attaqueSpe}\n - DefenseSpe : ${defenseSpe}\n - Potentiel : ${potentiel}\n"
        return textePresentation
    }
}