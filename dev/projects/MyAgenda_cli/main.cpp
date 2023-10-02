#include <stdio.h>
#include <iostream>
#include<string>
#include <limits>
#include "Agenda.h"
#include "Date.h"
#include "Heure.h"
using namespace std;

int main() {
    string fichier = "agenda.txt";
    Agenda a;
    a.load(fichier);
    cout << "Bienvenu dans votre agenda : " << endl << endl;
    bool quit = false;
    while(!quit){
        cout << "\t" << "1 - Ajouter un rendez-vous" << endl;
        cout << "\t" << "2 - Lister tous les rendez-vous dans l’ordre croissant de dates/heures" << endl;
        cout << "\t" << "3 - Afficher un rendez-vous saisi dans la liste" << endl;
        cout << "\t" << "4 - Modifier la date d’un rendez-vous" << endl;
        cout << "\t" << "5 - Modifier l'heure d’un rendez-vous" << endl;
        cout << "\t" << "6 - Supprimer un rendez-vous" << endl;
        cout << "\t" << "7 - Enregistrer l’agenda dans un fichier" << endl;
        cout << "\t" << "8 - Charger l’agenda à partir d’un fichier" << endl;
        cout << "\t" << "9 - Rechercher les rendez-vous par une date" << endl;
        cout << "\t" << "10 - Rechercher les rendez-vous par une heure" << endl;
        cout << "\t" << "11 - Rechercher les rendez-vous par un mot clé" << endl;
        cout << "\t" << "0 - Quitter l'application" << endl << endl;
        
        cout << "Entrer votre choix : " << endl;
        int choix = -1, c;
        while (!(cin >> choix)){
            cerr << "Erreur de saisie." << endl;
            cin.clear();
            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        }
        cout << endl;
        Date d;
        Heure h;
        string rdv;
        string f;
        switch(choix){
            case 1:
                cout << "Ajouter un rendez-vous : " << endl << endl;
                cout << "Entrer la date : " << endl;
                cin >> d;
                cout << "Entrer l'heure : " << endl;
                cin >> h;
                cout << "Entrer le rendez-vous : " << endl;
                cin >> rdv;
                a.add(d, h, rdv);
                cout << endl;
                a.saveAgenda(fichier);
                break;
            case 2:
                cout << "Lister tous les rendez-vous dans l’ordre croissant de dates/heures : " << endl << endl;
                a.afficher();
                cout << endl;
                break;
            case 3:
                cout << "Afficher un rendez-vous saisi dans la liste : " << endl << endl;
                cout << "Entrer la date : " << endl;
                cin >> d;
                cout << "Entrer l'heure : " << endl;
                cin >> h;
                cout << "Entrer le rendez-vous : " << endl;
                cin >> rdv;
                a.afficher(d, h, rdv);
                cout << endl;
                break;
            case 4:
                cout << "Modifier la date d’un rendez-vous : " << endl << endl;
                cout << "Entrer la date à changer : " << endl;
                cin >> d;
                cout << "Entrer l'heure de la date à changer : " << endl;
                cin >> h;
                cout << "Entrer le rendez-vous à changer : " << endl;
                cin >> rdv;
                cout << "Entrer le nombre de jour écartant le rendez-vous initial à celui finalement prévu : " << endl;
                while (!(cin >> c)){
                    cerr << "Erreur de saisie." << endl;
                    cin.clear();
                    cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                }
                a.modifDate(d, h, rdv, c);
                cout << endl;
                a.saveAgenda(fichier);
                break;
            case 5:
                cout << "Modifier l'heure d’un rendez-vous : " << endl << endl;
                cout << "Entrer la date de l'heure à changer : " << endl;
                cin >> d;
                cout << "Entrer l'heure à changer : " << endl;
                cin >> h;
                cout << "Entrer le nombre de minute écartant le rendez-vous initial à celui finalement prévu : " << endl;
                while (!(cin >> c)){
                    cerr << "Erreur de saisie." << endl;
                    cin.clear();
                    cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                }
                a.modifHeure(d, h, c);
                cout << endl;
                a.saveAgenda(fichier);
                break;
            case 6:
                cout << "Supprimer un rendez-vous : " << endl << endl;
                cout << "Entrer la date : " << endl;
                cin >> d;
                cout << "Entrer l'heure : " << endl;
                cin >> h;
                cout << "Entrer le rendez-vous : " << endl;
                cin >> rdv;
                a.erase(d, h, rdv);
                cout << endl;
                a.saveAgenda(fichier);
                break;
            case 7:
                cout << "Enregistrer l’agenda dans un fichier : " << endl << endl;
                cout << "Entrer le nom du fichier pour la sauvegarde : " << endl;
                cin >> f;
                a.saveAgenda(f);
                cout << endl;
                break;
            case 8:
                cout << "Charger l’agenda à partir d’un fichier : " << endl << endl;
                cout << "Entrer le nom du fichier à charger : " << endl;
                cin >> f;
                a.load(f);
                cout << endl;
                break;
            case 9:
                cout << "Rechercher les rendez-vous par une date : " << endl << endl;
                cout << "Entrer la date : " << endl;
                cin >> d;
                a.rechDate(d);
                cout << endl;
                break;
            case 10:
                cout << "Rechercher les rendez-vous par une heure : " << endl << endl;
                cout << "Entrer l'heure : " << endl;
                cin >> h;
                a.rechHeure(h);
                cout << endl;
                break;
            case 11:
                cout << "Rechercher les rendez-vous par un mot clé : " << endl << endl;
                cout << "Entrer le rendez-vous : " << endl;
                cin >> rdv;
                a.rechMot(rdv);
                cout << endl;
                break;
            case 0:
                cout << "Vous avez quitté l'application." << endl << endl;
                d.~Date();
                h.~Heure();
                rdv.~basic_string();
                quit = true;
                break;
            default:
                cerr << "Choix non-existant." << endl << endl;
                break;
        }
    }
    a.saveAgenda(fichier);
    return 0;
}
