#ifndef HEURE_H
#define HEURE_H
#include <iostream>
#include <string>

class Heure {
    friend std::istream &operator>>(std::istream &, Heure &);
    friend std::ostream &operator<<(std::ostream &, const Heure &);
public:
    Heure();
    Heure(const Heure &);
    Heure(const char *);
    Heure(const std::string &);
    Heure(int, int);
    virtual ~Heure();
    bool isHeure();
    Heure &operator=(const Heure &);
    Heure &operator=(const char *);
    Heure &operator=(const std::string &);
    Heure operator+(int);
    Heure &operator++();
    Heure operator++(int);
    int operator-(const Heure &);
    Heure operator-(int);
    Heure &operator--();
    Heure operator--(int);
    bool operator>(const Heure &) const;
    bool operator>=(const Heure &) const;
    bool operator<(const Heure &) const;
    bool operator<=(const Heure &) const;
    bool operator!=(const Heure &) const;
    bool operator==(const Heure &) const;
    operator std::string();
    std::string toString();
private:
    bool erreur_(int, int);
    void incr_();
    void decr_();
    int h_;
    int m_;
};

#endif // HEURE_H
