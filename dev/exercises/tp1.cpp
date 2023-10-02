#include <iostream>
#include <string>
#include <vector>
using namespace std;

int carre(int x){
    return x*x;
}

double carre(double x){
    return x*x;
}

long fibo(){
    long x = 0;
    cout << "Enter a value: ";
    while (x < 5) {
        cin >> x;
    }
    long int resultat=1;
    long int terme_1 = 1, terme_2 = 1;
    if (x < 3)
        return 1;
    resultat = 2;
    for (int C=3; C<=x; C++) {
        resultat = terme_1 + terme_2;
        terme_1 = terme_2;
        terme_2 = resultat;
    }
    return resultat;
}

 vector<string> inv(){
    string s;
    string res = "";
    cout << "Enter a string: ";
    getline(cin, s);
    vector<string> list;
    for (long i = s.length() - 1; i >= 0; i--) {
        res += s[i];
        if(s[i] == ' ') {
            list.push_back(res);
            res = "";
        }
        if( i == 0)
            list.push_back(res);
    }
    return list;
}

int main() {
    // Create a C++ program "Hello, World!" then compile and execute it.
    cout << "Hello, world" << endl;
    
    // Create the following program and correct all errors reported by the compiler.
    // STRING s = "Goodbye, cruel world!";
    // cOut << S << ’\n’;
    string s = "Goodbye, cruel world";
    cout << s << '\n';
    cout << endl;
    
    // Create a C++ program that allows you to read an integer entered from the keyboard and then calculate and display its square.
    int x;
    cout << "Enter an integer value: ";
    cin >> x;
    x = carre(x);
    cout << x << endl;
    cout << endl;
    
    // Create a C++ program that allows you to read a real number entered from the keyboard and then calculate and display its square.
    double xx;
    cout << "Enter a real value: ";
    cin >> xx;
    xx = carre(xx);
    cout << xx << endl;
    cout << endl;
    
    // Create a C++ program that allows you to read an integer (greater than 5) entered from the keyboard and then calculate and display its Fibonacci number.
    long y = fibo();
    cout << y << endl << endl;
    
    // Create a C++ program that allows you to read words entered from the keyboard, then store them in an object of type `std::vector<std::string>`, where each word corresponds to an object of type `std::string`. Print the reverse list of reversed words.
    cin.ignore( numeric_limits<streamsize>::max(), '\n' );
    vector<string> list = inv();
    for (int i = 0; i < list.size(); i++) {
        cout << list[i];
    }
    cout << endl;
    
    return 0;
}
