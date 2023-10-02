#include <iostream>
#include <vector>

/*
Create a C++ program that allows:
1. Reading an integer n from std::cin.
2. Creating a dynamic array using the new operator for n double-precision floating-point numbers.
3. Initializing all values in the array to 0.5.
4. Iterating through the array using a for loop (int i = 0; i < n; ++i), while printing to std::cout each value multiplied by i.
5. Finally, releasing the allocated memory space.
*/

int main(int argc, const char * argv[]) {
    // 1
    int n;
    std::cout << "Enter an integer:" << std::endl;
    std::cin >> n;
    //2 et 3
    //std::vector<int>* ptrVec = new std::vector<int>(5);
    std::vector<double>* tab = new std::vector<double>(n, 0.5);
    // 4
    for (int i = 0; i < n; i++) {
        std::cout << tab->at(i)*i << " ";
    }
    std::cout << std::endl;
    // 5
    delete tab;
    return 0;
}
