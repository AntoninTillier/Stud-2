create database TP1;
use TP1;
show tables;
create table R(A tinyint, B tinyint);
select A,B from R;
insert into R values (1,2);
insert into R values (4,2);

create table S(B tinyint, C tinyint);
insert into S values (2,3);
insert into S values (2,5);

create table T(A tinyint, B tinyint);
insert into T values(1,2);
insert into T values(2,3);

create table U(A tinyint, D tinyint);
insert into U values(1,2);
insert into U values(3,4);

select * from R;
select * from S;
select * from T;
select * from U;

/*exercice 1*/

select A,B from R where A=B;
select A from R where B= 2;
select R.B from R,S where R.B = S.B;
select S.B,U.A,U.D from S,U where S.C = U.A;
select U.A,T.B from T,U where T.A = 2 and U.A = T.B;
select T.A,S.C from T,S where S.B = T.B and S.C = 5;
select t1.B, t2.B from T t1, T t2 where t1.B = 2;
select R.A,S.B,S.C from R,S where R.B = S.B;

/*exercice 2*/

select * from T natural join R;
select * from S natural join T;
select * from S natural join U;
select * from (select A as C,D as D from U)X natural join S;
select * from R natural join R X;
select * from R natural join (select A as A, B as Bprime from R)X;
select * from R natural join S natural join T;

/*exercice 5*/

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

------------------------
--      QUERIES       --
------------------------

select horaire from salle where titre = "2046";
select titre from film where réalisateur = 'woody allen';
select acteur from film where titre = "2001";
select salle.nom from salle, film where salle.titre = film.titre and film.acteur = "ludivine sagnier";
select film.acteur from film, produit where film.acteur = produit.producteur;
select film.acteur from film, produit where film.acteur = produit.producteur and film.titre = produit.titre;
select acteur from film where réalisateur = "claude chabrol";
select film.acteur from film, produit where film.réalisateur = "claude chabrol" and produit.producteur = "claude chabrol" ;
select film.acteur from film, produit where film.acteur = produit.producteur and film.réalisateur = produit.producteur;
select film.acteur from film, produit where film.acteur = produit.producteur and film.réalisateur = produit.producteur and film.titre = produit.titre;
select film.acteur,film.acteurprime from film natural join film where film.titre = film.titreprime;select film.acteur,film.acteurprime from film natural join film where film.titre = film.titreprime ;
select film.acteur, X.acteur from (select film.titre,film.acteur from film)X natural join film where film.titre != X.titre;