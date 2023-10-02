#include <iostream>
#include <vector>

/*
Create a C++ program that allows:
1. Creating a std::vector for std::string strings.
2. Reading words from std::cin in a loop that ends with "/exit," adding the words to the vector.
3. Reading a word from std::cin.
4. Searching for the read word in the vector and printing a message at the end, for example, "exists" or "does not exist."
*/

int main(int argc, const char * argv[]) {
    // 1
    std::vector<std::string> tab;
    // 2
    std::string mot;
    while(std::cin>>mot&&mot!="/exit") {
        std::cout << "Enter a word: ";
        tab.push_back(mot);
    }
    // 3
    std::cout << std::endl;
    std::string mot2;
    std::cout << "Enter a word: ";
    std::cin >> mot2;
    for (int i = 0; i < tab.size(); i++) {
        if(tab[i] == mot2) {
            std::cout << "Word " << mot << " exists " << std::endl;
            break;
        }
        else
            std::cout << "Word " << mot << " does not exist" << std ::endl;
    }
    return 0;
}