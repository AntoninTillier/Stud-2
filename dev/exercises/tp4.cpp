#include <fstream>
#include <iostream>
#include <string>
#include <vector>

std::vector<std::string> tab;

bool recherche(const std::string &mot, std::ifstream &ifs) {
    std::string line;
    while(getline(ifs, line)) {
        if(line.find(mot)){
            return true;
        }
    }
    std::cout << std::endl;
    return false;
}

void inverserNewFichier(std::ifstream &ifs) {
    std::string line;
    while (getline(ifs, line)) {
        tab.push_back(line);
    }
    std::reverse(tab.begin(), tab.end());
    std::string fichier = "NewMots.txt";
    std::ofstream ofs(fichier.c_str());
    for (long i = 0; i < tab.size(); i++) {
        ofs << tab[i] << std::endl;
    }
}

int main(int argc, const char * argv[]) {
    // Create a program that allows you to check if a word entered via std::cin exists in a file entered by the user. It is sufficient to implement the function recherche().
    std::string mot;
    std::string fichier = "tp4_mots.txt";
    std::ifstream ifs(fichier.c_str());
    std::cout << "Enter a word: " << std::endl;
    std::cin >> mot;
    if (recherche(mot, ifs ))
        std::cout << "Word " << mot << " exists in " << fichier << std::endl;
    else
        std::cout << "Word " << mot << " does not exist in " << fichier << std::endl;
    
    // Create a text file named "mots.txt" containing at least 10 words, one per line. Create a program that reads this file, reverses the order of the words, and then saves everything in a new file entered by the user.
    ifs.clear ();
    ifs.seekg(0, std::ios::beg);
    inverserNewFichier(ifs);
    return 0;
}
