//
//  Agenda.h
//  Projet dev objet
//
//  Created by Antonin Tillier and Quentin Serrau on 6/05/2019.
//  Copyright © 2019 Antonin Tillier and Quentin Serrau. All rights reserved.
//

#ifndef AGENDA_H
#define AGENDA_H
#include <stdio.h>
#include <iomanip>
#include <iostream>
#include <cstdlib>
#include <string>
#include <map>
#include "Date.h"
#include "Heure.h"

class Agenda {
public:
    Agenda();
    Agenda(const std::map<Date, std::map<Heure,std::string>> &);
    virtual ~Agenda(); // destructeur
    void add(Date &, Heure &, std::string &); // ajoute un rendez-vous
    void afficher(); // lister tous les rendez-vous dans l’ordre croissant de Dates/heures
    void afficher(Date &, Heure &, std::string &); //afficher un rendez-vous saisi dans la liste
    void modifDate(Date &, Heure &, std::string &, int &); // modifie un Date de rendez-vous
    void modifHeure(Date &, Heure &, int &); // modifie un Heure de rendez-vous (bonus)
    void erase(Date &, Heure &, std::string &); // supprime un rendez - vous
    void saveAgenda(const std::string &); // sauvegarde un agenda
    Agenda &load(const std::string &); // charge un agenda
    void rechDate(Date &); // recherche par Date
    void rechHeure(Heure &); // recherche par Heure (bonus)
    void rechMot(std::string &); // recherche par mot clé
private:
    std::map<Date, std::map<Heure, std::string>> agenda_;
};

#endif // AGENDA_H
