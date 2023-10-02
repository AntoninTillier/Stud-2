#include "Date.h"
#include <iostream>
using namespace std;

int main()
{
    Date d0;
    cout << d0 << endl;
    Date d1 = "27/2/2015"; // JOUR/MOIS/ANNEE
    cout <<"d1 ++ : " << d1++ << endl;
    cout << "d1 : " << d1 << endl;
    Date d2 = d1 + 1;
    cout << "d2 : " << d2-- << endl;
    Date d3(d1 - 5);
    cout << "d3 : "<< d3 << endl;
    cout << "d2 - d3 : " << d2 - d3 << endl;
    Date d4 = --d3;
    cout << "d4 : " << d4 << endl;
    string s = d4;
    cout << "d4 mais string : " << s << endl;
    Date d5 = s;
    cout << "++d5 : " << ++d5 << endl;
    Date d6 = d5 + 7;
    cout << "d6 : " << d6 << endl;
    cin >> d6;
    cout << " cin d6 : " << d6 << endl;
    return 0;
}
