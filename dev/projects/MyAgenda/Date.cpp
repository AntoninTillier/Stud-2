#include "Date.h"
#include <stdio.h>
#include <iomanip>
#include <iostream>
#include <cstdlib>
#include <string>
using namespace std;

Date::Date() : a_(1970), m_(1), j_(1) {}

Date::Date(const Date &date) : a_(date.a_), m_(date.m_), j_(date.j_) {}

Date::Date(const char *d) : a_(1970), m_(1), j_(1) {
    char *a;
    char *p;
    p = (char*) memchr (d, '/', strlen(d));
    long slash = p - d ;
    char *s;
    s = (char*) memchr (d, '/', strlen(d));
    ++s;
    a = (char*) memchr(s, '/',strlen(s));
    ++a;
    j_ = (int) strtol(d, nullptr, 10);
    d = &d[slash + 1];
    m_ = (int) strtol(d, nullptr, 10);
    a_ = atoi(a);
    if(erreur_(a_, m_, j_)){
        a_ = 1970;
        m_ = 1;
        j_ = 1;
    }
}

Date::Date(const string &d) : a_(1970), m_(1), j_(1) {
    unsigned long slashA = d.find('/'), slashB = d.find_last_of('/');
    j_ = stoi(d.substr(0,slashA));
    m_ = stoi(d.substr(slashA + 1,slashB));
    a_ = stoi(d.substr(slashB + 1));
    if(erreur_(a_, m_, j_)){
        a_ = 1970;
        m_ = 1;
        j_ = 1;
    }
}

Date::Date(int a, int m, int j) : a_(a), m_(m), j_(j) {
    if (erreur_(a, m, j)) {
        a_ = 1970;
        m_ = 1;
        j_ = 1;
    }
}

Date::~Date() {}

bool Date::isDate() {
    if(erreur_(this -> a_, this -> m_, this -> j_))
        return false;
    else
        return true;
}

bool Date::bissextile_(int a) {
    return (a > 0) && ((a % 4 == 0 && a % 100 != 0) || a % 400 == 0);
}

bool Date::erreur_(int a, int m, int j) {
    if (a >= 1970 && m > 0 && m <= 12 && j > 0) {
        switch (m) {
            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                if (j <= 31)
                    return false;
                break;
            case 4:case 6:case 9:case 11:
                if (j <= 30)
                    return false;
                break;
            case 2:
                if (bissextile_(a) && j <= 29)
                    return false;
                else if (j <= 28)
                    return false;
                break;
            default:
                break;
        }
    }
    cerr << "*** Date erreur : " << j << "/" << m << "/" << a << "***" << endl;
    return true;
}

void Date::incr_() {
    switch (m_) {
        case 1:case 3:case 5:case 7:case 8:case 10:
            if (j_ == 31) {
                j_ = 1;
                ++m_;
            } else {
                ++j_;
            }
            break;
        case 4:case 6:case 9:case 11:
            if (j_ == 30) {
                j_ = 1;
                ++m_;
            } else {
                ++j_;
            }
            break;
        case 2:
            if (j_ < 28) {
                ++j_;
                break;
            }
            if (j_ == 29) {
                j_ = 1;
                ++m_;
                break;
            }
            if (bissextile_(a_)) {
                if (j_ == 28) {
                    ++j_;
                    break;
                }
            } else {
                if (j_ == 28) {
                    j_ = 1;
                    ++m_;
                    break;
                }
            }
            break;
        case 12:
            if (j_ == 31) {
                j_ = 1;
                m_ = 1;
                if (a_ == -1)
                    a_ = 1;
                else
                    ++a_;
            } else {
                ++j_;
            }
            break;
        default:
            break;
    }
}

void Date::decr_() {
    switch (m_) {
        case 1:
            if (j_ == 1) {
                j_ = 31;
                m_ = 12;
                if (a_ == 1)
                    a_ = -1;
                else
                    --a_;
            } else {
                --j_;
            }
            break;
        case 5:case 7:case 8:case 10:case 12:
            if (j_ == 1) {
                j_ = 30;
                --m_;
            } else {
                --j_;
            }
            break;
        case 4:case 6:case 9:case 11:
            if (j_ == 1) {
                j_ = 31;
                --m_;
            } else {
                --j_;
            }
            break;
        case 3:
            if (j_ == 1) {
                if (bissextile_(a_))
                    j_ = 29;
                else
                    j_ = 28;
                --m_;
            } else {
                --j_;
            }
            break;
        default:
            break;
    }
}

Date &Date::operator=(const Date &d) {
    this -> a_ = d.a_;
    this -> m_ = d.m_;
    this -> j_ = d.j_;
    return *this;
}

Date &Date::operator=(const char *d) {
    char *a;
    char *p;
    p = (char*) memchr (d, '/', strlen(d));
    long slash = p - d ;
    char *s;
    s = (char*) memchr (d, '/', strlen(d));
    ++s;
    a = (char*) memchr(s, '/',strlen(s));
    ++a;
    j_ = (int) strtol(d, nullptr, 10);
    d = &d[slash + 1];
    m_ = (int) strtol(d, nullptr, 10);
    a_ = atoi(a);
    if(erreur_(a_, m_, j_)){
        a_ = 1970;
        m_ = 1;
        j_ = 1;
    }
    return *this;
}

Date &Date::operator=(const string &d) {
    unsigned long slashA = d.find('/'), slashB = d.find_last_of('/');
    j_ = stoi(d.substr(0,slashA));
    m_ = stoi(d.substr(slashA + 1,slashB));
    a_ = stoi(d.substr(slashB + 1, d.size()));
    if(erreur_(a_, m_, j_)){
        a_ = 1970;
        m_ = 1;
        j_ = 1;
    }
    return *this;
}

Date Date::operator+(int j) {
    if(j > 0){
        while (j != 0) {
            this -> incr_();
            j --;
        }
    } else {
        while (j != 0) {
            this -> decr_();
            j ++;
        }
    }
    return *this;
}

Date &Date::operator++() {
    (*this).incr_();
    return *this;
}

Date Date::operator++(int) {
    Date d = *this;
    d.incr_();
    return d;
}

int Date::operator-(const Date &d) {
    int j = 0;
    Date date = *this;
    if(date == d)
        return  0;
    if(date > d) {
        while (date != d) {
            date.decr_();
            j --;
        }
        return j;
    } else {
        while (date != d) {
            date.incr_();
            j ++;
        }
        return j;
    }
}

Date Date::operator-(int j) {
    if(j > 0){
        while (j != 0) {
            this -> decr_();
            j --;
        }
    } else {
        while (j != 0) {
            this -> incr_();
            j ++;
        }
    }
    return *this;
}

Date &Date::operator--() {
    (*this).decr_();
    return *this;
}

Date Date::operator--(int) {
    Date d = *this;
    d.decr_();
    return d;
}

bool Date::operator>(const Date &d) const {
    if(a_ > d.a_){
        return true;
    } else if(a_ == d.a_){
        if(m_ > d.m_){
            return true;
        } else if(m_ == d.m_){
            if(j_ > d.j_) return true;
        }
    }
    return false;
}

bool Date::operator>=(const Date &d) const {
    return (Date::operator>(d) || Date::operator==(d));
}

bool Date::operator<(const Date &d) const {
    return !Date::operator>(d);
}

bool Date::operator<=(const Date &d) const {
    return !(Date::operator>(d) || Date::operator==(d));
}

bool Date::operator!=(const Date &d) const {
    return (a_ != d.a_ || m_ != d.m_ || j_!=d.j_);
}

bool Date::operator==(const Date &d) const {
    return ((a_ == d.a_) && (m_ == d.m_) && (j_ == d.j_));
}

Date::operator string() {
    if((m_ >= 0 && m_ <= 9) && (j_ >= 0 && j_ <= 9))
        return "0"+to_string(this -> j_) + "/0" + to_string(this -> m_) + "/" + to_string(a_);
    else if ((m_ >= 0 && m_ <= 9))
        return to_string(this -> j_) + "/0" + to_string(this -> m_) + "/" + to_string(a_);
    else if ((j_ >= 0 && j_ <= 9))
        return "0"+to_string(this -> j_) + "/" + to_string(this -> m_) + "/" + to_string(a_);
    else
        return to_string(this -> j_) + "/" + to_string(this -> m_) + "/" + to_string(a_);
}

string Date::toString() {
    if((m_ >= 0 && m_ <= 9) && (j_ >= 0 && j_ <= 9))
        return "0"+to_string(this -> j_) + "/0" + to_string(this -> m_) + "/" + to_string(a_);
    else if ((m_ >= 0 && m_ <= 9))
        return to_string(this -> j_) + "/0" + to_string(this -> m_) + "/" + to_string(a_);
    else if ((j_ >= 0 && j_ <= 9))
        return "0"+to_string(this -> j_) + "/" + to_string(this -> m_) + "/" + to_string(a_);
    else
        return to_string(this -> j_) + "/" + to_string(this -> m_) + "/" + to_string(a_);
}

ostream &operator<<(ostream &out, const Date &d) {
    if((d.m_ >= 0 && d.m_ <= 9) && (d.j_ >= 0 && d.j_ <= 9))
        out << "0" << d.j_ << "/0" << d.m_ << "/" << d.a_;
    else if ((d.m_ >= 0 && d.m_ <= 9))
        out << d.j_ << "/0" << d.m_ << "/" << d.a_;
    else if ((d.j_ >= 0 && d.j_ <= 9))
        out << "0" << d.j_ << "/" << d.m_ << "/" << d.a_;
    else
        out << d.j_ << "/" << d.m_ << "/" << d.a_;
    return out;
}

istream &operator>>(istream &in, Date &d) {
    string date;
    in >> date;
    d = date;
    return in;
}
