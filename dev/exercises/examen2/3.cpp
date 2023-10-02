#include <iostream>
#include <fstream>
#include <string>

/*
Create a C++ program that allows:
1. Reading a std::string character string from std::cin, which serves as a filename.
2. Creating an output file in text format using std::ofstream with the given name.
3. Reading non-zero numbers from std::cin in a loop that ends with "0," adding the numbers to the file.
4. Closing the output file.
5. Opening the same file using std::ifstream for reading.
6. Reading a number x from std::cin.
7. Printing all numbers in the file that are less than the number x.
8. Closing the opened file.
*/

int main(int argc, const char * argv[]) {
    // 1
    std::string filename;
    std::cout << "Enter a filename: ";
    std::cin >> filename;
    // 2
    std::ofstream outputFile(filename);
    // 3
    double num;
    std::cout << "Enter non-zero numbers: ";
    while (true) {
        std::cin >> num;
        if (num == 0) {
            break;
        }
        outputFile << num << " ";
    }
    // 4
    outputFile.close();
    // 5
    std::ifstream inputFile(filename);
    // 6
    double x;
    std::cout << "Enter a number x: ";
    std::cin >> x;
    // 7
    std::cout << "Numbers less than " << x << " in the file: ";
    while (inputFile >> num) {
        if (num < x) {
            std::cout << num << " ";
        }
    }
    std::cout << std::endl;
    // 8
    inputFile.close();
    return 0;
}