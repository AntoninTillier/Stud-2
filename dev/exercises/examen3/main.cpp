#include <iostream>
#include <map>
#include <string>
#include <cctype>
#include <algorithm>
using namespace std;

map<string, string> carnet;
map<string, string> resultat;

bool recherche(const string &s) {
    resultat.clear();
    for (std::map<string, string>::iterator it = carnet.begin(); it != carnet.end(); it++) {
        string sit = it -> second;
        transform(sit.begin(), sit.end(), sit.begin(), ::tolower);
        string ns = s;
        transform(ns.begin(), ns.end(), ns.begin(), ::tolower);
        if ( sit.find(ns) != string::npos){
            resultat.insert(pair<string, string>(it -> first, it -> second));
        }
    }
    if(resultat.size() > 0)
        return true;
    else
        return false;
}

int main() {
    // The exercises are based on `std::map<std::string, std::string> carnet;` and `std::map<std::string, std::string> resultat;`
    // which allow managing (adding and searching) a mini address book with only email addresses and first names.
    // Contacts will be stored in "carnet," and the result of each search will be stored in "resultat," with email addresses serving as keys.

    // Fill in the address book with the entered data (type //end to quit).
    while (1) {
        string email;
        string nom;
        cout << "Email address: ";
        cin >> email;
        if (email == "//end") {
            break;
        }
        cin.ignore();
        cout << "Last Name First Name: ";
        getline(cin, nom);
        carnet.insert(pair<string,string>(nom, email));
    }
    cout << endl;

    // Print the search results in the format Last Name First Name <email> per line.
    while (1) {
        string s;
        cout << "Search: ";
        cin >> s;
        if (s == "//end") {
            break;
        }
        if (recherche(s)) {
            for (std::map<string, string>::iterator it = resultat.begin(); it != resultat.end(); it++) {
                cout << it -> first << " <" << it -> second << ">" << endl;
            }
        } else {
            cout << "No results." << endl;
        }
    }
    // Search for contacts by traversing the address book to find all email addresses containing an entered substring (with at least 3 letters).
    // For example, if you enter "ab," then all contacts with "ab" in their email addresses should be added to the "resultat."
    // Please note that the search should be case-insensitive.
    while (1) {
        string s;
        cout << "Search: ";
        cin >> s;
        if (s == "//end") {
            break;
        }
        if(s.length() >= 3) {
            if (recherche(s)) {
                for (std::map<string, string>::iterator it = resultat.begin(); it != resultat.end(); it++) {
                    cout << it -> first << " <" << it -> second << ">" << endl;
                }
            } else {
                cout << "No results." << endl;
            }
        }
    }
    return 0;
}
