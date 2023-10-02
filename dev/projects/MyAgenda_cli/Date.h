#ifndef DATE_H
#define DATE_H
#include <iostream>
#include <string>

class Date {
    friend std::istream &operator>>(std::istream &, Date &);
    friend std::ostream &operator<<(std::ostream &, const Date &);
public:
    Date();
    Date(const Date &);
    Date(const char *);
    Date(const std::string &);
    Date(int, int, int);
    virtual ~Date();
    bool isDate();
    Date &operator=(const Date &);
    Date &operator=(const char *);
    Date &operator=(const std::string &);
    Date operator+(int);
    Date &operator++();
    Date operator++(int);
    int operator-(const Date &);
    Date operator-(int);
    Date &operator--();
    Date operator--(int);
    bool operator>(const Date &) const;
    bool operator>=(const Date &) const;
    bool operator<(const Date &) const;
    bool operator<=(const Date &) const;
    bool operator!=(const Date &) const;
    bool operator==(const Date &) const;
    operator std::string();
private:
    bool bissextile_(int);
    bool erreur_(int, int, int);
    void incr_();
    void decr_();
    int a_;
    int m_;
    int j_;
};

#endif // DATE_H
