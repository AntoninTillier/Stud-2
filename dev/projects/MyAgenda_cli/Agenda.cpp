#include "Agenda.h"
#include <stdio.h>
#include "stdlib.h"
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

Agenda::Agenda() {}

Agenda::Agenda(const map<Date, map<Heure, string>> &a) : agenda_(a){}

Agenda::~Agenda() {}

void Agenda::add(Date &d , Heure &h, string &rdv) {
    if(d.isDate() && h.isHeure()) {
        if(agenda_.empty()){
            map<Heure,string> hrdv;
            hrdv.insert(pair<Heure, string>(h,rdv));
            agenda_[d] = hrdv;
            hrdv.clear();
        } else {
            map<Date, map<Heure, string>>::iterator di = agenda_.begin();
            bool v = false;
            while (!v && di != agenda_.end()) {
                if(d == di -> first)
                    v = true;
                else
                     di++;
            }
            if(v){
                map<Heure, string>::iterator hi = di -> second.begin();
                v = false;
                while (!v && hi != di -> second.end()) {
                    if(h == hi -> first)
                        v = true;
                    else
                        hi++;
                }
                if(!v)
                    di -> second.insert(pair<Heure, string>(h,rdv));
            } else {
                map<Heure,string> hrdv;
                hrdv.insert(pair<Heure, string>(h,rdv));
                agenda_[d] = hrdv;
                hrdv.clear();
            }
        }
    } else
         cerr << "*** Date ou Heure erreur " << "***" << endl;
}

void Agenda::afficher(){
    if(agenda_.size() == 0)
        cout << "vide" << endl;
    else {
        for (map<Date, map<Heure, string>>::iterator di = agenda_.begin(); di != agenda_.end(); di++) {
            for (map<Heure, string>::iterator hrdv = di ->second.begin(); hrdv != di -> second.end(); hrdv++) {
                cout << di -> first << " - " << hrdv -> first << "< " << hrdv -> second << " >" << endl;
            }
        }
    }
}

void Agenda::afficher(Date &d, Heure &h, std::string &rdv) {
    if(d.isDate() && h.isHeure()){
        for (map<Date, map<Heure, string>>::iterator di = agenda_.begin(); di != agenda_.end(); di++) {
            for (map<Heure, string>::iterator hrdv = di ->second.begin(); hrdv != di -> second.end(); hrdv++) {
                if(d == di -> first && h == hrdv -> first && rdv == hrdv -> second)
                    cout << di -> first << " - " << hrdv -> first << "< " << hrdv -> second << " >" << endl;
            }
        }
    } else
        cerr << "*** Date ou Heure erreur " << "***" << endl;
}

void Agenda::modifDate(Date &d, Heure &h, string &rdv, int & c) {
    if(d.isDate() && h.isHeure()){
        map<Date, map<Heure, string>>::iterator di = agenda_.begin();
        bool v = false;
        while (!v && di != agenda_.end()) {
            if(d == di -> first)
                v = true;
            else
                di++;
        }
        if(v) {
            map<Heure, string>::iterator hi = di -> second.begin();
            v = false;
            while (!v && hi != di -> second.end()) {
                if(h == hi -> first)
                    v = true;
                else
                    hi++;
            }
            if(v){
                if(rdv == hi ->second) {
                    Date td = di -> first;
                    if(c > 0) {
                        map<Heure,string> hrdv;
                        hrdv.insert(pair<Heure, string>(h, rdv));
                        agenda_.insert(pair<Date, map<Heure, string>>(d + c, hrdv));
                        hrdv.clear();
                        this -> erase(td, h, rdv);
                        td.~Date();
                    } else {
                        map<Heure,string> hrdv;
                        hrdv.insert(pair<Heure, string>(h, rdv));
                        agenda_.insert(pair<Date, map<Heure, string>>(d - abs(c), hrdv));
                        hrdv.clear();
                        this -> erase(td, h, rdv);
                        td.~Date();
                    }
                }
                else
                    cerr << "rdv pas à l'heure ni a la date dans l'agenda" << endl;
            } else
                cerr << "Heure pas dans la date de l'agenda" << endl;
        } else
            cerr << "Date pas dans l'agenda" << endl;
    } else
         cerr << "*** Date ou Heure erreur " << "***" << endl;
}

void Agenda::modifHeure(Date &d, Heure &h, int &c){
    if(d.isDate() && h.isHeure()){
        map<Date, map<Heure, string>>::iterator di = agenda_.begin();
        bool v = false;
        while (!v && di != agenda_.end()) {
            if(d == di -> first)
                v = true;
            else
                di++;
        }
        if(v) {
            map<Heure, string>::iterator hi = di -> second.begin();
            v = false;
            while (!v && hi != di -> second.end()) {
                if(h == hi -> first)
                    v = true;
                else
                    hi++;
            }
            if(v){
                Heure th = hi -> first;
                string trdv = hi -> second;
                this -> erase(d, th, trdv);
                th.~Heure();
                trdv.~basic_string();
                if(c > 0) {
                    Heure nh = h +c;
                   this -> add(d, nh, hi -> second);
                } else {
                    Heure nh = h - abs(c);
                    this -> add(d, nh, hi -> second);
                }
            } else
                cerr << "Heure pas dans la date de l'agenda" << endl;
        } else
            cerr << "Date pas dans l'agenda" << endl;
    } else
        cerr << "*** Date ou Heure erreur " << "***" << endl;
}

void Agenda::erase(Date &d, Heure &h, string &rdv) {
    if(d.isDate() && h.isHeure()){
        map<Date, map<Heure, string>>::iterator di = agenda_.begin();
        bool v = false;
        while (!v && di != agenda_.end()) {
            if(d == di -> first)
                v = true;
            else
                di++;
        }
        if(v){
            map<Heure, string>::iterator hi = di -> second.begin();
            v = false;
            while (!v && hi != di -> second.end()) {
                if(h == hi -> first)
                    v = true;
                else
                    hi++;
            }
            if(v){
                if(rdv == hi ->second) {
                    if(di -> second.size() == 1)
                        agenda_.erase(di);
                    else
                        di -> second.erase(hi);
                }
                else
                    cerr << "rdv pas à l'heure ni a la date dans l'agenda" << endl;
            } else
                cerr << "Heure pas dans la date de l'agenda" << endl;
                
        } else
            cerr << "Date pas dans l'agenda" << endl;
    } else
        cerr << "*** Date ou Heure erreur " << "***" << endl;
}

void Agenda::saveAgenda(const string &fichier) {
    ofstream out(fichier.c_str());
    if(agenda_.size() == 0)
        cout << "vide" << endl;
    else {
        for (map<Date, map<Heure, string>>::iterator di = agenda_.begin(); di != agenda_.end(); di++) {
            for (map<Heure, string>::iterator hrdv = di ->second.begin(); hrdv != di -> second.end(); hrdv++) {
                out << di -> first << "-" << hrdv -> first << "-" << hrdv -> second << endl;
            }
        }
        if(out.is_open())
            cout << "Agenda enregistré" << endl;
        out.clear();
        out.close();
    }
}

Agenda &Agenda::load(const string &fichier) {
    ifstream in(fichier.c_str());
    std::string line;
    Date d;
    Heure h;
    string rdv;
    while(getline(in, line)) {
        long tiretA = line.find('-'), tiretB = line.find_last_of('-');
        d = line.substr(0,tiretA);
        h = line.substr(tiretA + 1,tiretB - (tiretA + 1));
        rdv = line.substr(tiretB + 1);
        this -> add(d, h, rdv);
    }
    if(!in.is_open())
        cout << "Fichier non chargé" << endl;
    in.clear ();
    in.seekg(0, ios::beg);
    in.close();
    return *this;
}

void Agenda::rechDate (Date &d) {
    if(d.isDate()){
        cout << d << " :" << endl;
        for (std::map<Date, map<Heure, string>>::iterator a = agenda_.begin(); a != agenda_.end(); a++) {
            for (std::map<Heure, string>::iterator hrdv = a ->second.begin(); hrdv != a -> second.end(); hrdv++) {
                if(a -> first == d)
                    cout << "\t" << hrdv -> first << "< " << hrdv -> second << " >" << endl;
            }
        }
    } else
        cerr << "*** Date erreur : " << endl;
}

void Agenda::rechHeure(Heure &h) {
    if(h.isHeure()){
        cout << h << " :" << endl;
        for (std::map<Date, map<Heure, string>>::iterator a = agenda_.begin(); a != agenda_.end(); a++) {
            for (std::map<Heure, string>::iterator hrdv = a ->second.begin(); hrdv != a -> second.end(); hrdv++) {
                if(hrdv -> first == h)
                    cout << "\t" << a -> first << " - " << hrdv -> first << "< " << hrdv -> second << " >" << endl;
            }
        }
    } else
        cerr << "*** Heure erreur : " << endl;
}

void Agenda::rechMot(string &m) {
    for (std::map<Date, map<Heure, string>>::iterator a = agenda_.begin(); a != agenda_.end(); a++) {
        for (std::map<Heure, string>::iterator hrdv = a ->second.begin(); hrdv != a -> second.end(); hrdv++) {
            if(hrdv -> second == m)
                cout << "\t" << a -> first << " :" << hrdv -> first << "< " << hrdv -> second << " >" << endl;
        }
    }
}
