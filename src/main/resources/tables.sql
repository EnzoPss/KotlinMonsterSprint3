CREATE TABLE Entraineurs(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    argents INTEGER);


CREATE TABLE EspeceMonstre (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom CHAR(255),
    type CHAR(255),
    baseAttaque INT,
    baseDefense INT,
    baseVitesse INT,
    baseAttaqueSpe INT,
    baseDefenseSpe INT,
    basePv INT,
    modAttaque DOUBLE,
    modDefense DOUBLE,
    modVitesse DOUBLE,
    modAttaqueSpe DOUBLE,
    modDefenseSpe DOUBLE,
    modPv DOUBLE,
    description TEXT,
    particularites TEXT,
    caracteres TEXT
);


CREATE TABLE IndividuMonstre (
     id INT PRIMARY KEY AUTO_INCREMENT,
     nom CHAR(255),
     niveau INT,
     attaque INT,
     defense INT,
     vitesse INT,
     attaqueSpe INT,
     defenseSpe INT,
     pvMax INT,
     potentiel DOUBLE,
     exp DOUBLE,
     pv INT,

    espece_id INT DEFAULT NULL REFERENCES EspeceMonstre(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    entraineur_equipe_id INT DEFAULT NULL REFERENCES Entraineurs(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    entraineur_boite_id INT DEFAULT NULL REFERENCES Entraineurs(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE

);
