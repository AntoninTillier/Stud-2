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
    std::string afficher(); // lister tous les rendez-vous dans l’ordre croissant de Dates/heures
    void modifDate(Date &, Heure &, std::string &, int &); // modifie un Date de rendez-vous
    void modifHeure(Date &, Heure &, int &); // modifie un Heure de rendez-vous (bonus)
    void modifRdv(Date &, Heure &, std::string &, std::string &); // modifier un rendez-vous (bonus)
    void erase(Date &, Heure &, std::string &); // supprime un rendez - vous
    void saveAgenda(const std::string &); // sauvegarde un agenda
    Agenda &load(const std::string &); // charge un agenda
    std::string rechDate(Date &); // recherche par Date
    std::string rechHeure(Heure &); // recherche par Heure (bonus)
    std::string rechMot(std::string &); // recherche par mot clé
private:
    std::map<Date, std::map<Heure, std::string>> agenda_;
};

#endif // AGENDA_H
