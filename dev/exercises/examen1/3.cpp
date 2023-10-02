#include<fstream>
#include <iostream>
#include <vector>
#include <string>
#include <cstring>

/*
Create a C++ program that allows:
1. Reading a std::string character string from std::cin, which serves as a filename.
2. Creating an output file in text format using std::ofstream with the name read.
3. Reading words from std::cin in a loop that ends with "/exit," adding the words to the file.
4. Closing the output file.
5. Opening the same file using std::ifstream for reading.
6. Reading a word from std::cin.
7. Searching for the read word in the file and printing a message at the end, for example, "exists" or "does not exist."
8. Closing the opened file.
*/

int main(int argc, const char * argv[]) {
    std::string mot;
    std::cout << "Enter a file: " << std::endl;
    std::cin >> mot;
    std::string mot2;
    std::ofstream ofs(mot.c_str());
     while(std::cin>>mot2&&mot2!="/exit") {
         ofs << mot2;
     }
    ofs.close();
    std::ifstream ifs(mot.c_str());
    std::string line;
    std::string mot3;
    std::cout << "Enter a word: " << std::endl;
    std::cin >> mot3;
    while(getline(ifs, line)) {
        if(line.find(mot3))
            std::cout << "Word " << mot << " exists in " << mot << std::endl;
        else
            std::cout << "Word " << mot << " does not exist in " << mot << std::endl;
    }
    ifs.close();
    return 0;
}