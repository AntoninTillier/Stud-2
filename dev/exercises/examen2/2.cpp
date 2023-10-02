#include <iostream>
#include <vector>

/*
Create a C++ program that allows:
1. Creating a std::vector for double-precision floating-point numbers.
2. Reading non-zero numbers from std::cin in a loop that ends with "0," adding the numbers to the vector.
3. Reading a number x from std::cin.
4. Printing all numbers in the vector that are less than the number x.
*/

int main(int argc, const char * argv[]) {
    // 1
    std::vector<double> numbers;
    // 2
    double num;
    std::cout << "Please enter non-zero numbers: ";
    while (true) {
        std::cin >> num;
        if (num == 0) {
            break;
        }
        numbers.push_back(num);
    }
    // 3
    double x;
    std::cout << "Enter an number x: ";
    std::cin >> x;
    // 4
    std::cout << "Numbers less than " << x << " : ";
    for (double num : numbers) {
        if (num < x) {
            std::cout << num << " ";
        }
    }
    std::cout << std::endl;
    return 0;
}