package org.example

import org.example.dresseur.Entraineur
import org.example.item.Badge
import org.example.item.MonsterKube
import org.example.jeu.Partie
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2,"Rival",500)

var especeSpringleaf = EspeceMonstre(1,	"Springleaf",	"Graine",	60,	9,	11,	10,	12,	14,	34.0,	6.5,	9.0,	8.0,	7.0,	10.0,	"Petit monstre espiègle rond comme une graine, adore le soleil.",	"Sa feuille sur la tête indique son humeur.",	"Curieux, amical, timide")
var especeFlamkip = EspeceMonstre(4,	"Flamkip",	"Animal",	50,	12,	8,	13,	16,	7,	22.0,	10.0,	5.5,	9.5,	9.5,6.5,	"Petit animal entouré de flammes, déteste le froid.",	"Sa flamme change d’intensité selon son énergie.",	"Impulsif, joueur, loyal")
var especeAquamy = EspeceMonstre(7,	"Aquamy",	"Meteo",	55,	10,	11,	9,	14,	14,	27.0,	9.0,	10.0,	7.5,	12.0,	12.0,	"Créature vaporeuse semblable à un nuage, produit des gouttes pures.",	"Fait baisser la température en s’endormant.",	"Calme, rêveur, mystérieux")
var especeLaoumi = EspeceMonstre(8,	"Laoumi",	"Animal",	58,	11,	10,	9,	8,	11,	23.0,	11.0,	8.0,	7.0,	6.0,	11.5,	"Petit ourson au pelage soyeux, aime se tenir debout.",	"Son grognement est mignon mais il protège ses amis.",	"Affectueux, protecteur, gourmand")
var especeBugsyface = EspeceMonstre(10,	"Bugsyface",	"Insecte",	45,	10,	13,	8,	7,	13,	21.0,	7.0,	11.0,	6.5,	8.0,	11.5,	"Insecte à carapace luisante, se déplace par bonds et vibre des antennes.",	"Sa carapace devient plus dure après chaque mue.",	"Travailleur, sociable, infatigable")
var especeGalum = EspeceMonstre(13,	"Galum",	"Minéral",	55,	12,	15,	6,	8,	12,	13.0,	9.0,	13.0,	4.0,	6.5,	10.5,	"Golem ancien de pierre, yeux lumineux en garde.",	"Peut rester immobile des heures comme une statue.",	"Sérieux, stoïque, fiable")

var zone1 = Zone(1,"Zone du début", 600, mutableListOf(especeSpringleaf, especeFlamkip, especeAquamy), null, null )
var zone2 = Zone(2,"Zone après le début", 800, mutableListOf(especeLaoumi, especeBugsyface, especeGalum), null, null)


//val monstre1 = IndividuMonstre(1, "springleaf", especeSpringleaf, null, 1500.0)
//val monstre2 = IndividuMonstre(2, "flamkip", especeFlamkip, null, 1500.0)
//val monstre3 = IndividuMonstre(3, "aquamy", especeAquamy, null, 1500.0)

//val badgePierre = Badge(1, "Badge roche", "c'est un badge", null)

var kube1 = MonsterKube(1, "le kube",
    "kube de capture", 50.0)


fun nouvellePartie(): Partie{
    println("Bonjour et bienvenu !! \n Quel est votre nom ?")
    var nomJoueur = readln()
    joueur.nom = nomJoueur

    var nouvelleGame: Partie = Partie(1, joueur, zone1)
    return nouvelleGame
}


fun main() {

    zone1.zoneSuivante = zone2
    zone2.zonePrecedante = zone1
    joueur.sacAItems.add(kube1)

    val partie = nouvellePartie()
    partie.choixStarter()
    partie.jouer()

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
