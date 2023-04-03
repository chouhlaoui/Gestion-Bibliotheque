
CREATE TABLE IF NOT EXISTS abonne (
  id int(5) PRIMARY KEY,
  NomPrenom varchar(100) NOT NULL,
  Proffession varchar(100)
) ;


INSERT INTO abonne (id, NomPrenom, Proffession) VALUES
(1, 'TARGOTO CHRISTIAN', 'élève'),
(2, 'NGARKIRA HYACINTHE', 'étudiant'),
(3, 'KOURABE YVES','élève'),
(4, 'ZENABA YOUNOUS', 'enseignant'),
(5, 'FATIME DAGO','élève'),
(6, 'JEANNINE ROUTOUANG', 'autre'),
(7, 'RAISSA BETEL', 'étudiant');


CREATE TABLE IF NOT EXISTS livre (
  IDLivre int(5) PRIMARY KEY,
  titre varchar(90) NOT NULL,
  auteur varchar(50) DEFAULT NULL,
  categorie varchar(50) NOT NULL,
  NBCopie INT(3)
) ;


INSERT INTO livre (IDLivre, titre, auteur, categorie, NBCopie) VALUES
(1, "Les miserables", "Victor Hugo", "Roman", 5),
(2, 'Tragedie', NULL, 'Revue', 1),
(3, 'le soleil des independance', NULL, 'Nouvelle', 2),
(4, 'Republique à vendre', NULL, 'Journal', 0),
(5, 'Tartarin de Tarascon', NULL, 'Roman', 4),
(6, 'Etudiant de soweto', NULL, 'Nouveauté', 5),
(7, 'une vie de boy', NULL, 'Roman', 1);

INSERT INTO livre (IDLivre, titre, auteur, categorie, NBCopie) VALUES
(17, "Les miserables", "Victor Hugo", "Roman", 5);

select * from livre;

CREATE TABLE Emprunt (
  ID INT PRIMARY KEY AUTO_INCREMENT ,
  AbID INT(5),
  LivID INT(5),
  Sortie DATE,
  Retour DATE DEFAULT NULL,
  FOREIGN KEY (AbID) REFERENCES abonne(id),
  FOREIGN KEY (LivID) REFERENCES livre(IDLivre)
);

INSERT INTO emprunt(AbID,LivID,Sortie) VALUES (4,52,STR_TO_DATE('20-4-2023', '%d-%m-%Y'));

create VIEW acceuil as
select l.titre, l.IDLivre, a.NomPrenom, e.Sortie, a.id from emprunt as e
JOIN livre as l on (LivID = IDLivre) 
JOIN abonne as a on(AbID = a.id);



