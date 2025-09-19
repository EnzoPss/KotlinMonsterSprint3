package org.example

import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2,"Regis",200)

var monstre1 = EspeceMonstre(1,	"Springleaf",	"Graine",	60,	9,	11,	10,	12,	14,	34.0,	6.5,	9.0,	8.0,	7.0,	10.0,	"Petit monstre espiègle rond comme une graine, adore le soleil.",	"Sa feuille sur la tête indique son humeur.",	"Curieux, amical, timide")
var monstre2 = EspeceMonstre(4,	"Flamkip",	"Animal",	50,	12,	8,	13,	16,	7,	22.0,	10.0,	5.5,	9.5,	9.5,6.5,	"Petit animal entouré de flammes, déteste le froid.",	"Sa flamme change d’intensité selon son énergie.",	"Impulsif, joueur, loyal")
var monstre3 = EspeceMonstre(7,	"Aquamy",	"Meteo",	55,	10,	11,	9,	14,	14,	27.0,	9.0,	10.0,	7.5,	12.0,	12.0,	"Créature vaporeuse semblable à un nuage, produit des gouttes pures.",	"Fait baisser la température en s’endormant.",	"Calme, rêveur, mystérieux")
var monstre4 = EspeceMonstre(8,	"Laoumi",	"Animal",	58,	11,	10,	9,	8,	11,	23.0,	11.0,	8.0,	7.0,	6.0,	11.5,	"Petit ourson au pelage soyeux, aime se tenir debout.",	"Son grognement est mignon mais il protège ses amis.",	"Affectueux, protecteur, gourmand")
var monstre5 = EspeceMonstre(10,	"Bugsyface",	"Insecte",	45,	10,	13,	8,	7,	13,	21.0,	7.0,	11.0,	6.5,	8.0,	11.5,	"Insecte à carapace luisante, se déplace par bonds et vibre des antennes.",	"Sa carapace devient plus dure après chaque mue.",	"Travailleur, sociable, infatigable")
var monstre6 = EspeceMonstre(13,	"Galum",	"Minéral",	55,	12,	15,	6,	8,	12,	13.0,	9.0,	13.0,	4.0,	6.5,	10.5,	"Golem ancien de pierre, yeux lumineux en garde.",	"Peut rester immobile des heures comme une statue.",	"Sérieux, stoïque, fiable")

var zone1 = Zone(1,"Zone du début", 2000, mutableListOf(monstre1, monstre2, monstre3), null, null )
var zone2 = Zone(2,"Zone après le début", 10_000, mutableListOf(monstre4, monstre5, monstre6), null, null)


//val monstre7 = IndividuMonstre(1, "springleaf", 1500.0, especeSpringLeaf)
//val monstre8 = IndividuMonstre(2, "flamkip", 1500.0, especeFlamkip)
//val monstre9 = IndividuMonstre(3, "aquamy", 1500.0, especeAquamy)



fun main() {

    zone1.zoneSuivante = zone2
    zone2.zonePrecedante = zone1
    
    joueur.afficheDetail()
    rival.afficheDetail()
    joueur.argents+=50
    joueur.afficheDetail()

    println(monstre1.afficheArt())
    println(monstre2.afficheArt())
    println(monstre3.afficheArt())
    println(monstre4.afficheArt())
    println(monstre5.afficheArt())
    println(monstre6.afficheArt())

}


/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */
fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron" -> "\u001B[38;5;94m"
        else -> "" // pas de couleur si non reconnue
    }
    return "$codeCouleur$message$reset"
}
