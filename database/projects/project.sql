USE bdd_ge;

/* Conjunctive Queries */

-- Selection, projection, join --

-- What are the names of the products that have left the warehouse without redundancy?

SELECT DISTINCT nomProduit FROM palette,produit, historique WHERE historique.typeModif = "supp" AND historique.idPalette = palette.idPalette AND palette.idProduit = produit.idProduit;

-- What are the pieces of information inside the racka?

SELECT DATE_FORMAT(dateEntree, '%d/%m/%Y') AS dateE, DATE_FORMAT(datePeremption, '%m/%Y') AS dateP, nomProduit, produit.codeProduit, palette.numLot, description FROM racka AS rack, palette, produit, lot WHERE rack.idPalette=palette.idPalette AND palette.idProduit=produit.idProduit AND produit.codeProduit=lot.codeProduit;


--  Selection, projection, join, nesting --

-- What are the idPalettes and their names that were taken out between 2018-11-20 and 2018-12-01?

SELECT idPalette, nomProduit FROM palette , produit WHERE idPalette IN (SELECT  idPalette FROM historique WHERE dateMod BETWEEN '2018-11-20' AND '2018-12-01') AND palette.idProduit = produit.idProduit;

-- What are the idPalettes that are in the product and have the description equal to ERIC and are in the racka?

SELECT idPalette FROM produit,palette WHERE produit.codeProduit IN (SELECT codeProduit FROM lot,palette WHERE lot.numLot = palette.numLot AND description = "ERIC") AND palette.idProduit = produit.idProduit AND idPalette IN(SELECT idPalette FROM racka);

/* Relational Queries */

-- Selection, projection, join, union --

-- What are the idPalettes, level, and location of racka and rackb where the locations are full?

SELECT * FROM racka WHERE racka.idPalette != 0 UNION SELECT * FROM rackb WHERE rackb.idPalette != 0;

-- What are all the palettes from racka?

SELECT idPalette FROM racka WHERE idPalette != 0 UNION SELECT idPalette FROM historique WHERE rack = 'A';


-- Selection, projection, join, union, difference --

-- What are the names of the products from the palettes that are not in the racks?

SELECT nomProduit FROM palette, produit WHERE palette.idProduit = produit.idProduit AND idPalette NOT IN (SELECT idPalette FROM racka UNION SELECT idPalette FROM rackb UNION SELECT idPalette FROM rackc);

-- What are the idPalettes, expiration date, entry date (numLot), level, location, product name, product code from racks where the product is not ERIC, ISABELLE, or CLEAN?

(SELECT DISTINCT racka.idPalette, DATE_FORMAT(datePeremption, '%Y/%m') AS dateP, DATE_FORMAT(dateEntree, '%Y/%m') AS dateE, palette.numLot, niveau, emplacement, nomProduit, produit.codeProduit FROM palette, lot, produit, racka WHERE lot.codeProduit = produit.codeProduit and palette.numLot = lot.numLot and racka.idPalette = palette.idPalette AND racka.idPalette NOT IN (SELECT idPalette FROM palette WHERE description = "ERIC")) UNION ALL(SELECT DISTINCT rackb.idPalette, DATE_FORMAT(datePeremption, '%Y/%m') AS dateP, DATE_FORMAT(dateEntree, '%Y/%m') AS dateE, palette.numLot, niveau, emplacement, nomProduit, produit.codeProduit FROM palette, lot, produit, rackb WHERE lot.codeProduit = produit.codeProduit and palette.numLot = lot.numLot  and rackb.idPalette = palette.idPalette AND rackb.idPalette NOT IN (SELECT idPalette FROM palette WHERE description = "ISABELLE")) UNION ALL(SELECT DISTINCT rackc.idPalette, DATE_FORMAT(datePeremption, '%Y/%m') AS dateP, DATE_FORMAT(dateEntree, '%Y/%m') AS dateE, palette.numLot, niveau, emplacement, nomProduit, produit.codeProduit FROM palette, lot, produit, rackc WHERE lot.codeProduit = produit.codeProduit and palette.numLot = lot.numLot  and rackc.idPalette = palette.idPalette AND rackc.idPalette NOT IN (SELECT idPalette FROM palette WHERE description = "CLEAN"));


-- Division --

-- What are the products that have been in all the racks made by "PARFUME STORE"?

SELECT DISTINCT nomProduit FROM (SELECT idPalette FROM racka UNION SELECT idPalette FROM rackb UNION SELECT idPalette FROM rackc) R, palette, produit 
WHERE R.idPalette = palette.idPalette AND palette .idProduit = produit.idProduit AND R.idPalette NOT IN 
(SELECT RR.idPalette FROM (SELECT idPalette FROM racka UNION SELECT idPalette FROM rackb UNION SELECT idPalette FROM rackc) RR, 
(SELECT idProduit, description FROM palette WHERE description = "PARFUME STORE") T  WHERE (RR.idPalette, T.idProduit, T.description ) NOT IN 
(SELECT idPalette, idProduit, description FROM palette));

-- QUERY OF THE FUTURE

SELECT DISTINCT nomProduit FROM (SELECT idPalette FROM racka UNION SELECT idPalette FROM rackb UNION 
SELECT idPalette FROM rackc) R, palette, lot, produit WHERE R.idPalette = palette.idPalette AND palette .idProduit = produit.idProduit AND palette.numLot = lot.numLot AND R.idPalette NOT IN 
(SELECT RR.idPalette FROM (SELECT idPalette FROM racka UNION SELECT idPalette FROM rackb UNION 
SELECT idPalette FROM rackc) RR, (SELECT rack FROM historique WHERE dateMod BETWEEN '2018-11-20' AND '2018-12-01') T  WHERE (RR.idPalette, T.rack ) NOT IN 
(SELECT palette.idPalette, rack FROM palette, historique)); 

SELECT rack FROM historique WHERE dateMod BETWEEN '2018-11-20' AND '2018-12-01';

/* Extended Relational Queries */

-- Selection, projection, join, aggregation --

-- Get the total weight of palettes per product name.

SELECT produit.nomProduit, SUM(palette.poids) FROM produit, palette WHERE palette.idProduit = produit.idProduit GROUP BY produit.nomProduit;

-- What is the number of palettes in racka?

SELECT COUNT(idPalette ) FROM racka WHERE idPalette != 0;


-- Selection, projection, join, difference, aggregation --

-- Get the total weight of palettes per product name, where the weight is greater than or equal to 400 and they are not in racka.

SELECT produit.nomProduit, SUM(palette.poids) FROM produit, palette WHERE palette.idProduit = produit.idProduit AND palette.poids >= 400 AND palette.idPalette NOT IN 
(SELECT palette.idPalette FROM racka, palette WHERE racka.idPalette=palette.idPalette) GROUP BY produit.nomProduit;

-- What are the products that are not in racka and their total number of units?

SELECT nomProduit, SUM(palette.unite)FROM palette, produit, lot WHERE palette.idProduit=produit.idProduit AND produit.codeProduit=lot.codeProduit AND nomProduit NOT IN 
( SELECT nomProduit FROM racka, palette, produit, lot WHERE racka.idPalette=palette.idPalette AND palette.idProduit=produit.idProduit AND produit.codeProduit=lot.codeProduit) GROUP BY nomProduit;


-- Selection, projection, join, union, difference, aggregation --

-- What is the number of palettes still present in the warehouse and not in racka?

SELECT COUNT(idPalette) AS NbPal FROM palette WHERE idPalette NOT IN (SELECT idPalette FROM historique WHERE typeModif = "supp" UNION SELECT idPalette FROM racka);

-- What is the average of the palettes that are not in the racks per idProduct?

SELECT AVG(NbPal) FROM (SELECT idProduit, COUNT(idPalette) AS NbPal FROM palette WHERE idPalette NOT IN (SELECT idPalette FROM racka UNION SELECT idPalette FROM rackb UNION SELECT idPalette FROM rackc) GROUP BY idProduit) X;
