
CREATE TABLE IF NOT EXISTS abonne (
  id int(5) PRIMARY KEY,
  NomPrenom varchar(100) NOT NULL,
  Proffession varchar(100)
) ;


INSERT INTO abonne (id, NomPrenom, Proffession) VALUES
(1, 'ahmed hmaidi', 'Elève'),
(2, 'chourouk hlaoui', 'Etudiant'),
(3, 'kamel echaieb','Ensignant'),
(4, 'sameh mezni', 'Enseignant'),
(5, 'fatma hlaoui','Elève'),
(6, 'cyrine zekri', 'Autre'),
(7, 'khalil aouini', 'Etudiant'),
(8, 'isasam mezni', 'Etudiant');


CREATE TABLE IF NOT EXISTS livre (
  IDLivre int(5) PRIMARY KEY,
  titre varchar(90) NOT NULL,
  auteur varchar(50) DEFAULT NULL,
  categorie varchar(50) NOT NULL,
  NBCopie INT(3)
) ;


INSERT INTO livre (IDLivre, titre, auteur, categorie, NBCopie) VALUES
(1, "Les miserables", "Victor Hugo", "Roman", 5),
(2, 'Revue internationale de Géomatique', NULL, 'Revue', 1),
(3, 'Le Horla', NULL, 'Nouvelle', 2),
(4, 'Republique à vendre', NULL, 'Journal', 0),
(5, 'Tartarin de Tarascon', NULL, 'Roman', 4),
(6, 'It starts with us', NULL, 'Nouveauté', 5),
(7, 'une vie de boy', NULL, 'Roman', 1),
(8, 'It ends with us', NULL, 'Nouveauté', 5);


CREATE TABLE Emprunt (
  ID INT PRIMARY KEY AUTO_INCREMENT ,
  AbID INT(5),
  LivID INT(5),
  Sortie DATE,
  Retour DATE DEFAULT NULL,
  FOREIGN KEY (AbID) REFERENCES abonne(id),
  FOREIGN KEY (LivID) REFERENCES livre(IDLivre)
);

create VIEW acceuil as
select l.titre, l.IDLivre, a.NomPrenom, e.Sortie, a.id from emprunt as e
JOIN livre as l on (LivID = IDLivre) 
JOIN abonne as a on(AbID = a.id);


CREATE TABLE NonDispoLivre (
  titre varchar(90) NOT NULL,
  auteur varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS AncienAbonne (
  NomPrenom varchar(100) NOT NULL
) ;

