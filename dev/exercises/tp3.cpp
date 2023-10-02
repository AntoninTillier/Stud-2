#include <iostream>
#include <vector>

std::string secret = "Bonbon";
std::vector<std::string> mots;

void strinv(std::string *s) {
    std::string res = *s;
    for (long i = res.size(); i >= 0; i--) {
        std::cout << res[i];
    }
    std::cout << std::endl;
}

void strinv1(std::string &s) {
    for (long i = s.length(); i >= 0; i--) {
        std::cout << s[i];
    }
   std::cout << std::endl;
}

std::string strinv2(std::string s) {
    std::string res;
    for (long i = s.length(); i >= 0; i--) {
        res += s[i];
    }
    return res;
}

std::string lire() {
    std::string res;
    std::cout << "What is the secret word?" << std::endl;
    std::cin >> res;
    return res;
}

void secret() {
    std::string m = lire();
    while (m != "") {
        if(m == secret) {
            std::cout << "The secret word is " << m << " !" << std::endl;
            std::cout << std::endl;
            std::cout << mots.size() << " try" << (mots.size() > 1 ? "s" : "") << " : " << std::endl;
            std::cout << std::endl;
            for (long i = 0; i < mots.size(); i++) {
                std::cout << "-- " << (i + 1) << " : " << mots[i] << " :(" << std::endl;
            }
            std::cout << "-- " << mots.size() << " : " << m << " :)" << std::endl;
            return;
    }
    mots.push_back(m);
    m = lire();
    }
}

int main(int argc, const char * argv[]) {
    
    // "Implement and test the three versions of strinv that allow reversing a std::string."
    std::string s = "azerty";
    strinv(&s);
    std::cout << std::endl;
    strinv1(s);
    std::cout << std::endl;
    s =  strinv2(s);
    std::cout << s << std::endl;
    std::cout << std::endl;
    std::cout << std::endl;

    // Create a C++ program "secret" without defining any classes.
    secret();
    std::cout << std::endl;
    return 0;
}
