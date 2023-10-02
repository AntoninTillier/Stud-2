#include <iostream>

void array_init (int x [], int n, int v) {
    int i = 0;
    while (i < n) {
        x[i] = v;
        i++;
    }
}

bool find_letter(char *s, char l) {
    bool find = false;
    for (int i = 0; i <= strlen(s); i++) {
        if(s[i] == l)
            find = true;
    }
    return find;
}

void reverse_char_string(char *s){
    for (long i = strlen(s); i >= 0; i--) {
        std::cout << s[i];
    }
}

int main(int argc, const char * argv[]) {
    // Create a function `array_init(int x[], int n, int v)` that initializes an array `x` of `n` integers of type `int` with the value `v`.
    int n = 10;
    int t[n];
    array_init(t, n, 2);
    for (int i = 0; i< n; i++) {
        std::cout << t[i] << "  ";
    }
    std::cout << std::endl;

    // Create a program that allows you to read a C-style string and a letter, then using pointers, determine if the letter is included in the string.
    char s[] = "COUCOU";
    char l = 'A';
    std::cout << find_letter(s, l) << std::endl;
    
    // Create a program that allows you to read a C-style string (a sufficiently large array, for example, char c[200]) using std::cin and then display its reverse on the screen using a pointer.
    char c[200];
    std::cin >> c;
    reverse_char_string(c);
    std::cout << std::endl;
    
    // Create a program to check the values of ptr and *ptr, where ptr is a pointer of type int, according to the cases below.
    // (*ptr)++
    // ++(*ptr)
    // *(ptr++)
    // *(++ptr)
    // *++ptr
    // ++*ptr
    // *ptr++
    int x[5];
    int *ptr;
    for (int i = 0; i < 5; ++i)
        x[i]=0;
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "(*ptr)++ = " << (*ptr)++ << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "++(*ptr) = " << ++(*ptr) << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "*(ptr)++ = " << *(ptr)++ << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "*(ptr ++) = " << *(ptr ++) << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "*(++ ptr) = " << *(++ ptr) << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "*(ptr)++ = " << (*ptr)++ << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "*++ ptr = " << *++ ptr << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "++ *ptr = " << ++ *ptr << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    ptr = x;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << "*ptr ++ = " << *ptr ++ << std::endl;
    std::cout << " ptr = " << ptr << std::endl;
    std::cout << "*ptr = " << *ptr << std::endl;
    std::cout << std::endl;
    
    return 0;
}
