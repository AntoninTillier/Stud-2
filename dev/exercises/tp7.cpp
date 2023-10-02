#include <iostream>
#include <fstream>
#include <string>
#include <map>

std::string filter(std::string s){
    std::string res = "";
    std::string fg = "«";
    std::string fd = "»";
    std::string alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
        if(s[i] >= '0' && s[i] <= '9'){
            if((s[i + 1] >= 'a' && s[i + 1] <= 'z') || (s[i + 1] >= 'A' && s[i + 1] <= 'Z')){
                res = "";
                break;
            }
        } else if (s[i] == '(' || s[i] == ')' || s[i] == '.' || s[i] == ',' || s[i] == '"' || s[i] == ':' || s[i] == ';' || s[i] == '?') {
            continue;
        } else if(s[i] == '-'){
            if((s[i + 1] >= 'a' && s[i + 1] <= 'z') || (s[i + 1] >= 'A' && s[i + 1] <= 'Z'))
                res += s[i];
            else
                continue;
        } else {
            if(s[i] == fg[0] || s[i] == fd[0]){
                if(s[i + 1] == fg[1] || s[i + 1] == fd[1]){
                    i = i + 1;
                }
            } else {
                res += s[i];
            }
        }
    }
    if(res.size() == 1){
        for (int i = 0; alphabet.size(); i++) {
            if(res[0] == alphabet[i]) {
                res = "";
                break;
            }
        }
    }
    return res;
}

int main(int argc, const char * argv[]) {
    std::string fichier = "tp6_article.txt";
    std::map<std::string, int> mapOfWords;
    std::ifstream ifs2(fichier.c_str());
    std::string m;
    while (ifs2 >> m) {
        if (m.length()) {
            m = filter(m);
            if(m != "") {
                if(mapOfWords.count(m)) {
                    std::multimap<std::string, int>::iterator it = mapOfWords.find(m);
                    if (it != mapOfWords.end())
                        it->second++;
                }
                else
                    mapOfWords.insert(std::pair<std::string, int>(m,1));
            }
        }
    }
    ifs2.clear ();
    ifs2.close();
    int k = 0;
    std::cout << "Enter the value for the most frequently used words: " << std::endl;
    std::cin >> k;
    for (std::map<std::string, int>::iterator it = mapOfWords.begin(); it != mapOfWords.end(); it++) {
        if(it->second >= k)
           std::cout << it -> first << " " << it -> second << std::endl;
    }
    std::cout << "\n";
    std::cout << "\n";
    std::cout << "\n";
    std::cout << "\n";
    return 0;
}
