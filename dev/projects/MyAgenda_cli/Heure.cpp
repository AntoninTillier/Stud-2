#include "Heure.h"
#include <stdio.h>
#include <iomanip>
#include <iostream>
#include <cstdlib>
using namespace std;

Heure::Heure() : h_(0), m_(0){}

Heure::Heure(const Heure &heure) : h_(heure.h_), m_(heure.m_){}

Heure::Heure(const char *heure) : h_(0), m_(0) {
    char *p;
    p = (char*) memchr (heure, ':', strlen(heure));
    long c = p - heure;
    h_ =(int) strtol(heure, NULL, 10);
    heure = &heure[c + 1];
    m_ = (int) strtol(heure, NULL, 10);
    if(erreur_(h_, m_)){
        h_ = 0;
        m_ = 0;
    }
}

Heure::Heure(const string &heure) : h_(0), m_(0) {
    long c = heure.find(':');
    h_ = stoi(heure.substr(0,c));
    m_ = stoi(heure.substr(c + 1));
    if(erreur_(h_, m_)){
        h_ = 0;
        m_ = 0;
    }
}

Heure::Heure(int h, int m) : h_(h), m_(m) {
    if(erreur_(h, m)){
        h_ = 0;
        m_ = 0;
    }
}

Heure::~Heure() {}

bool Heure::isHeure() {
    return !erreur_(this -> h_, this -> m_);
}

bool Heure::erreur_(int h, int m) {
    if( (h >= 0 && h <= 23) && (m >= 0 && m <= 59)) {
        return false;
    } else {
        cerr << "*** Heure erreur : " << h << ":" << m << "***" << endl;
        return true;
    }
}

void Heure::incr_() {
    if(m_ % 59 == 0 & m_ != 0) {
       ++ h_;
        m_ = 0;
    } else
        ++ m_;
}

void Heure::decr_() {
    if(m_ % 59 == 0 && m_ != 59){
        -- h_;
        m_ = 59;
    } else
        -- m_;
}

Heure &Heure::operator=(const Heure &h) {
    this -> h_ = h.h_;
    this -> m_ = h.m_;
    return *this;
}

Heure &Heure::operator=(const char *h) {
    char *p;
    p = (char*) memchr (h, ':', strlen(h));
    long c = p - h ;
    h_ =(int) strtol(h, NULL, 10);
    h = &h[c + 1];
    m_ = (int) strtol(h, NULL, 10);
    if(erreur_(h_, m_)){
        h_ = 0;
        m_ = 0;
    }
    return *this;
}

Heure &Heure::operator=(const string &h) {
    long c = h.find(':');
    h_ = stoi(h.substr(0,c));
    m_ = stoi(h.substr(c + 1,h.size()));
    if(erreur_(h_, m_)){
        h_ = 0;
        m_ = 0;
    }
    return *this;
}

Heure Heure::operator+(int m) {
    if(m > 0){
        while (m > 0) {
            this -> incr_();
            m --;
        }
    } else {
        while (m < 0) {
            this -> decr_();
            m ++;
        }
    }
    return *this;
}

Heure &Heure::operator++() {
    (*this).incr_();
    return *this;
}

Heure Heure::operator++(int) {
    Heure h = *this;
    h.incr_();
    return h;
}

int Heure::operator-(const Heure &h) {
    int m = 0;
    Heure heure = *this;
    if(heure == h)
        return  0;
    if(heure > h) {
        while (heure != h) {
            heure.decr_();
            m --;
        }
        return m;
    } else {
        while (heure != h) {
            heure.incr_();
            m ++;
        }
        return m;
    }
}

Heure Heure::operator-(int m) {
    if(m > 0){
        while (m > 0) {
            this -> decr_();
            m --;
        }
    } else {
        while (m < 0) {
            this -> incr_();
            m ++;
        }
    }
    return *this;
}

Heure &Heure::operator--() {
    (*this).decr_();
    return *this;
}

Heure Heure::operator--(int) {
    Heure d = *this;
    d.decr_();
    return d;
}

bool Heure::operator>(const Heure &h) const {
    if(h_ > h.h_){
        return true;
    } else if(h_ == h.h_){
        if(m_ > h.m_){
            return true;
        }
    }
    return false;
}

bool Heure::operator>=(const Heure &h) const {
    return (Heure::operator>(h) || Heure::operator==(h));
}

bool Heure::operator<(const Heure &h) const {
    return !Heure::operator>(h);
}

bool Heure::operator<=(const Heure &h) const {
    return !(Heure::operator>(h) || Heure::operator==(h));
}

bool Heure::operator!=(const Heure &h) const {
    return (h_ != h.h_ || m_ != h.m_);
}

bool Heure::operator==(const Heure &h) const {
    return ((h_ == h.h_) && (m_ == h.m_));
}

Heure::operator string() {
    if((m_ >= 0 && m_ <= 9) && (h_ >= 0 && h_ <= 9))
        return "0"+to_string(this -> h_)+":0"+to_string(this ->m_);
    else if ((m_ >= 0 && m_ <= 9))
        return to_string(this -> h_) + ":0" + to_string(this -> m_);
    else if ((h_ >= 0 && h_ <= 9))
        return "0"+to_string(this -> h_) + ":" + to_string(this -> m_);
    else
        return to_string(this -> h_) + ":" + to_string(this -> m_);
}

ostream &operator<<(ostream &out, const Heure &h) {
    if((h.m_ >= 0 && h.m_ <= 9) && (h.h_ >= 0 && h.h_ <= 9))
        out << "0" << h.h_ << ":0" << h.m_;
    else if ((h.m_ >= 0 && h.m_ <= 9))
        out << h.h_ << ":0" << h.m_;
    else if ((h.h_ >= 0 && h.h_ <= 9))
        out << "0" << h.h_ << ":" << h.m_;
    else
        out << h.h_ << ":" << h.m_;
    return out;
}

istream &operator>>(istream &in, Heure &h) {
    string heure;
    in >> heure;
    h = heure;
    return in;
}
