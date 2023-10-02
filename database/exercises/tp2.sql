/*
CREATE DATABASE TD;
USE TD;
create table R(A tinyint, B tinyint);
create table T(A tinyint, B tinyint);
create table S(B tinyint, C tinyint);
create table U(A tinyint, D tinyint);
*/
insert into R values(1,2);
insert into R values(4,2);
insert into T values(1,2);
insert into T values(2,3);
insert into S values(2,3);
insert into S values(2,5);
insert into U values(1,2);
insert into U values(3,4);

-- exercice 2

SELECT * FROM R union all Select * FROM T;
select distinct R.A,R.B from R,T where R.A = T.A and R.B = T.B and (R.A, R.B) not in (Select * from S);
select distinct T.B From T where T.B not in (Select T.A from T);
Select distinct S.C from S where (S.B,S.C) not in (Select * from R) and (S.B, S.C) not in (Select * from T where (T.A, T.B) not in (Select * from U));
Select distinct V.A from (Select T1.A, T2.B from T T1, T T2)V where (V.A, V.B) not in (Select * from T);
Select * from (Select * from R union (Select B as a, C as b from S))uni where (a,b) not in (select * from t);

-- Algebraic

Select distinct * from R where (R.A, R.B) not in (Select * from U);
Select distinct R.A, R.B from R,U where R.A != U.A and R.B != U.D;
Select distinct B from R natural join S where A = C or C = 3;
Select distinct R.A, T.A from R, T where R.A != 1 and (T.A = 2 or T.B = 2);
Select * from R where (A, B) not in (Select * from T) union (Select * from T where (A, B) not in (Select * from R));
Select * from S where (B,C) not in (Select B, C from S);
Select * from S where (S.B, S.C) not in (Select S1.B, S2.C from S S1, S S2);
Select B from S where B not in (Select A from T) union (Select B from S where (B, C) not in (Select * from T);
 
 
 create database cinema;
use cinema;
--------------------
-- TABLE CREATION --
--------------------

CREATE TABLE salle (
       nom VARCHAR(20),
       horaire TIME,
	titre VARCHAR(40)
);
CREATE TABLE film (
       titre VARCHAR(40),
       réalisateur VARCHAR(30),
	acteur VARCHAR(30)
);
CREATE TABLE produit (
       producteur VARCHAR(30),
       titre VARCHAR(40)
);
CREATE TABLE vu (
       spectateur VARCHAR(30),
       titre VARCHAR(40)
);
CREATE TABLE aime (
       spectateur VARCHAR(30),
       titre VARCHAR(40)
);


--------------------
-- DATA INSERTION --
--------------------

insert into film values('indiana jones','steven spielberg','harrison ford');
insert into film values('starwars','george lucas','harrison ford');
insert into film values('starwars','george lucas','carrie fisher');
insert into film values('indiana jones','steven spielberg','sean connery');
insert into film values('american graffiti','george lucas','harrison ford');
insert into film values('annie hall','woody allen','woody allen');
insert into film values('annie hall','woody allen','diane keaton');
insert into film values('2001','stanley kubrick','an ape');
insert into film values('2046','kar-wai wong','tony chiu wai leung');
insert into film values('good will hunting','gus van zant','matt damon');
insert into film values('life of gus van zant','gus van zant','gus van zant');
insert into film values('planes','klay hall','dane cook');
insert into film values('planes','klay hall','stacy keach');
insert into film values('planes','klay hall','teri hatcher');
insert into film values('planes','john lasseter','owen wilson');
insert into film values('planes','john lasseter','paul newman');
insert into film values('planes','john lasseter','bonnie hunt');
insert into film values('frozen','chris buck','kristen bell');
insert into film values('frozen','chris buck','idina menzel');
insert into produit values('matt damon','good will hunting');
insert into produit values('sean connery','good will hunting');
insert into produit values('gus van zant','good will hunting');
insert into produit values('traci balthazor','planes');
insert into produit values('darla anderson','cars');
insert into produit values('peter del vecho','frozen');
insert into produit values('woody allen','annie hall');
insert into salle values('capucin','20:00','indiana jones');
insert into salle values('lobbies','20:00','indiana jones');
insert into salle values('capucin','20:00','2046');
insert into salle values('lobbies','20:00','good will hunting');
insert into salle values('capucin','20:00','indiana jones');
insert into salle values('capcine','14:00','cars');
insert into salle values('capcine','16:00','planes');
insert into salle values('capcine','14:00','frozen');
insert into salle values('capcine','16:00','frozen');
insert into vu values('s1','starwars');
insert into vu values('s1','indiana jones');
insert into vu values('s2','indiana jones');
insert into vu values('s1','american graffiti');
insert into aime values('s2','indiana jones');
insert into aime values('s3','starwars');

-- exercice 3
 
Select acteur from film where réalisateur= "lucas";
Select spectateur from aime where (spectatateur, titre) not in (Select * from vu);
Select F1.acteur, F2.acteur from film F1, film F2 where F.1titre = F2.titre and F1.réalisateur = F2.réalisateur and F1.acteur != F2.acteur;
Select acteur from film where réalisateur = "lucas" and acteur not in (Select acteur from film where réalisateur != "lucas");
Select distinct acteur from film where acteur not in (Select acteur from film, (Select titre, réalisateur from film where réalisateur = "lucas" ) r  where (r.titre, r.réalisateur, acteur) not in (Select * from film));
(Select acteur from film where réalisateur = "lucas" and acteur not in (Select acteur from film where réalisateur != "lucas")) intersect  (Select distinct acteur from film where acteur not in (Select acteur from film, (Select titre, réalisateur from film where réalisateur = "lucas" ) r  where (r.titre, r.réalisateur, acteur) not in (Select * from film)));

-- exercice 4

Select spectateur, count(titre) from vu group by spectateur;
Select titre, count(distinct nom) from salle group by titre;
Select acteur, count(titre) from film group by acteur having count(titre) > 3;
Select producteur, count(produit.titre) from film, produit where film.titre = produit.titre and film.titre not in (Select titre from salle) group by producteur;
Select avg(nb) from (Select spectateur, count(titre) as nb from vu group by spectateur) T;
Select avg(nb) from (Select acteur, count(titre) as nb from film group by acteur) T;
Select acteur from (Select acteur, max(nb) as m from (Select acteur, count(film.titre) as nb from film,salle where film.titre = salle.titre group by acteur) A group by acteur having m = nb) B group by acteur;