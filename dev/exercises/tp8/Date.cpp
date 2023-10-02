#include "Date.h"
#include <iomanip>
#include <iostream>
#include <cstdlib>
using namespace std;

// Implement all the TODO functions and operators below for the Date class.

Date::Date() : a_(1970), m_(1), j_(1) {}

Date::Date(const Date &date) : a_(date.a_), m_(date.m_), j_(date.j_) {}

Date::Date(const char *d) : a_(1970), m_(1), j_(1) {
    // TODO
    int slashA = 0, slashB = 0;
    for (int i = 0; d[i] != '\0'; i++) {
        if(d[i] == '/') {
            if(slashA == 0)
                slashA = i;
            else
                slashB = i;
        }
        if(slashA != 0 && slashB != 0)
            break;
    }
   
    j_ =(int) strtol(d, NULL, 10);
    d = &d[slashA + 1];
    m_ = (int) strtol(d, NULL, 10);
    d = &d[(slashB%2) + 2];
    a_ = (int) strtol(d, NULL, 10);
    if(erreur_(a_, m_, j_)){
        a_ = 1970;
        m_ = 1;
        j_ = 1;
    }
}

Date::Date(const std::string &d) : a_(1970), m_(1), j_(1) {
    // TODO
    int slashA = 0, slashB = 0;
    for (int i =0; i < d.size(); i++) {
        if(d.at(i) == '/'){
            if(slashA == 0)
                slashA = i;
            else
                slashB = i;
        }
        if(slashA != 0 && slashB != 0)
            break;
    }
    j_ = stoi(d.substr(0,slashA));
    m_ = stoi(d.substr(slashA + 1,slashB));
    a_ = stoi(d.substr(slashB + 1, d.size()));
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

bool Date::bissextile_(int a) {
    return (a > 0) && ((a % 4 == 0 && a % 100 != 0) || a % 400 == 0);
}

bool Date::erreur_(int a, int m, int j) {
    // The attribute 'a' could be negative to represent BC (Before Christ).
    if (a != 0 && m > 0 && m <= 12 && j > 0) {
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
    cout << "*** Date error: " << j << "/" << m << "/" << a << "***" << endl;
    return true;
}

void Date::incr_() {
    // TODO
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
    // TODO
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
    // TODO
    this -> a_ = d.a_;
    this -> m_ = d.m_;
    this -> j_ = d.j_;
    return *this;
}

Date &Date::operator=(const char *d) {
    // TODO
    int slashA = 0, slashB = 0;
    for (int i = 0; d[i] != '\0'; i++) {
        if(d[i] == '/') {
            if(slashA == 0)
                slashA = i;
            else
                slashB = i;
        }
        if(slashA != 0 && slashB != 0)
            break;
    }
    
    j_ =(int) strtol(d, NULL, 10);
    d = &d[slashA + 1];
    m_ = (int) strtol(d, NULL, 10);
    d = &d[(slashB%2) + 2];
    a_ = (int) strtol(d, NULL, 10);
    if(erreur_(a_, m_, j_)){
        a_ = 1970;
        m_ = 1;
        j_ = 1;
    }
    return *this;
}

Date &Date::operator=(const std::string &d) {
    // TODO
    int slashA = 0, slashB = 0;
    for (int i =0; i < d.size(); i++) {
        if(d.at(i) == '/'){
            if(slashA == 0)
                slashA = i;
            else
                slashB = i;
        }
        if(slashA != 0 && slashB != 0)
            break;
    }
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
    // TODO
    while (j != 0) {
        this -> incr_();
        j --;
    }
    return *this;
}

Date &Date::operator++() {
    // TODO
    this->incr_();
    return *this;
}

Date Date::operator++(int) {
    // TODO
    Date d = *this;
    this -> incr_();
    return *this;
}

int Date::operator-(const Date &d) {
    // TODO
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
    // TODO
    while (j != 0) {
        this -> decr_();
        j --;
    }
    return *this;
}

Date &Date::operator--() {
    // TODO
    this -> decr_();
    return *this;
}

Date Date::operator--(int) {
    // TODO
    Date d = *this;
    this -> decr_();
    return *this;
}

bool Date::operator>(const Date &d) const {
    // TODO
    bool j =false, m = false,a = false;
    if(this -> j_ > d.j_)
        j = true;
    if(this -> m_ > d.m_)
        m = true;
    if(this -> a_ > d.a_)
        a = true;
    return j+m+a;
}

bool Date::operator>=(const Date &d) const {
    // TODO
    bool j =false, m = false,a = false;
    if(this -> j_ >= d.j_)
        j = true;
    if(this -> m_ >= d.m_)
        m = true;
    if(this -> a_ >= d.a_)
        a = true;
    return j+m+a;
}

bool Date::operator<(const Date &d) const {
    // TODO
    bool j =false, m = false,a = false;
    if(this -> j_ < d.j_)
        j = true;
    if(this -> m_ < d.m_)
        m = true;
    if(this -> a_ < d.a_)
        a = true;
    return j+m+a;
}

bool Date::operator<=(const Date &d) const {
    // TODO
    bool j =false, m = false,a = false;
    if(this -> j_ <= d.j_)
        j = true;
    if(this -> m_ <= d.m_)
        m = true;
    if(this -> a_ <= d.a_)
        a = true;
    return j+m+a;
}

bool Date::operator!=(const Date &d) const {
    // TODO
    bool j =false, m = false,a = false;
    if(this -> j_ == d.j_)
        j = true;
    if(this -> m_ == d.m_)
        m = true;
    if(this -> a_ == d.a_)
        a = true;
    if(j && m && a)
        return false;
    else
        return true;
}

bool Date::operator==(const Date &d) const {
    // TODO
    bool j =false, m = false,a = false;
    if(this -> j_ == d.j_)
        j = true;
    if(this -> m_ == d.m_)
        m = true;
    if(this -> a_ == d.a_)
        a = true;
    if(j && m && a)
        return true;
    else
        return false;
}

Date::operator std::string() {
    // TODO
    return to_string(this -> j_) + "/" + to_string(this -> m_) + "/" + to_string(a_);
}

std::ostream &operator<<(std::ostream &out, const Date &d) {
    // TODO
    out << d.j_ << "/" << d.m_ << "/" << d.a_;
    return out;
}

std::istream &operator>>(std::istream &in, Date &d) {
    // TODO
    string date;
    in >> date;
    d = date;
    return in;
}
