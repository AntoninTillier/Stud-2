#include <iostream>
#include <string>

/*
Create a C++ program that allows:
1. Reading an integer n from std::cin.
2. Creating a dynamic array using the new operator for n std::string character strings.
3. Initializing all strings in the array to "abc."
4. Iterating through the array using a for loop (int i = 0; i < n; ++i), while printing to std::cout each string concatenated with "!".
5. Finally, releasing the allocated memory space.
*/

int main(int argc, const char * argv[]) {
    // 1
    int n;
    std::cout << "Enter an integer n: ";
    std::cin >> n;
    // 2
    std::string* strings = new std::string[n];
    // 3
    for (int i = 0; i < n; ++i) {
        strings[i] = "abc";
    }
    // 4
    for (int i = 0; i < n; ++i) {
        std::cout << strings[i] << "!";
    }
    std::cout << std::endl;
    // 5
    delete[] strings;
    return 0;
}
