package org.example.jeu

import Utilisable
import org.example.dresseur.Entraineur
import org.example.joueur
import org.example.monstre.IndividuMonstre
import org.example.monde.Zone

class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre
) {

    var round: Int = 1


    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
        var defaite = true
        for (monstre in joueur.equipeMonstre){
            if (monstre.pv >= 0) defaite = false
        }
        return defaite
    }


    fun joueurGagne(): Boolean {
        while (monstreSauvage.pv <= 0) {
            println("${joueur.nom} a gagné !")
            var gainExp = monstreSauvage.exp * 0.20
            monstreJoueur.exp += gainExp
            println("${monstreJoueur.nom} gagne ${gainExp} exp")
            return true
        }
        if (monstreSauvage.entraineur == joueur) {
            println("${monstreSauvage.nom} a été capturé !")
            return true
        } else return false
    }


    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) monstreSauvage.attaquer(monstreJoueur)
    }


    fun actionJoueur(): Boolean {
        if (gameOver() == true) return false

        println("Menu d'actions :\n 1. Attaquer    2. SacItem    3. EquipeMonstre")
        var choix = readln().toInt()

        if (choix == 1) {
            monstreJoueur.attaquer(monstreSauvage)
        }

        else if (choix.toInt()==2){//choix objet
            for ((index,objet) in joueur.sacAItems.withIndex()){
                println("$index => ${objet.nom}")
            }
            var choixObjet = readln().toString()
            while ( choixObjet.toInt() !in (0..joueur.sacAItems.size)){
                println("Veuillez saisir une action valable")
                choixObjet = readln().toString()
            }
            val obj= joueur.sacAItems[choixObjet.toInt()]
            if(obj is Utilisable){
                val result= obj.utiliser(monstreSauvage)
                if (result){
                    return false
                }
            }else{println("Objet non utilisable")}


        }
        else{//changer de monstre
            for ((index,monster) in joueur.equipeMonstre.withIndex()){
                println("$index => ${monster.nom}")
            }
            var choixMonster = readln()
            while ( choixMonster.toInt() !in (0..joueur.equipeMonstre.size)){
                println("Veuillez saisir un monstre encore en vie")
                choixMonster = readln()
            }
            val autreMonstre =joueur.equipeMonstre[choixMonster.toInt()]
            if (autreMonstre.pv <=0  ){
                println("Impossible ! Ce monstre est KO")
            }
            else{
                println("${autreMonstre} remplace ${monstreJoueur}")
                monstreJoueur=autreMonstre
            }
        }
        return true
    }


    fun afficherCombat() {
        println("======== Début Round : ${round} ========\n" +
        "Niveau : ${monstreSauvage.niveau}\n" +
        "Pv : ${monstreSauvage.pv} / ${monstreSauvage.pvMax}\n" +
        monstreSauvage.espece.afficheArt(true) +
        monstreJoueur.espece.afficheArt(false) +
        "Niveau : ${monstreJoueur.niveau}\n" +
        "Pv : ${monstreJoueur.pv}  ${monstreJoueur.pvMax}")
    }


    fun jouer() {

        if(monstreJoueur.pv == 0 && gameOver()==false){
            for (unMonstre in joueur.equipeMonstre){
                if (unMonstre.pv>0){
                    monstreJoueur=unMonstre
                }
            }
        }
        afficherCombat()
        if (monstreJoueur.vitesse >= monstreSauvage.vitesse) {
            if (actionJoueur() == false) return
            actionAdversaire()
        }
        else{
            actionAdversaire()
            if (gameOver() == false) actionJoueur()
        }

    }


    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */
    fun lanceCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }



}