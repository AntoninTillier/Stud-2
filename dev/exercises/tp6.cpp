#include <iostream>
#include <fstream>
#include <string>
#include<set>
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
    // Using std::set, create a program that extracts and prints on the screen all distinct words from the file "article.txt" while adhering to the following two criteria:
    // A word is a character string consisting of letters, with or without hyphens and apostrophes (for example, "famille," "celui-ci," and "c’est-à-dire" are words, but "123bonjour" and "bon123bonjour" are not words).
    // All words that end with numbers or punctuation marks should be extracted without these characters (for example, "lui14." should be "lui," and "jours," should be "jours").
    std::set<std::string> S;
    std::string fichier = "tp6_article.txt";
    std::ifstream ifs(fichier.c_str());
    std::string s;
    while (ifs >> s) {
        s = filter(s);
        if(s != "")
            S.insert(s);
        s.clear();
    }
    ifs.clear ();
    ifs.close();
    
    for (std::set<std::string>::iterator it=S.begin(); it != S.end(); ++it) {
        std::cout << ' ' << *it;
    }
    std::cout << "\n";
    std::cout << "\n";
    
    // Using std::map, create a program that counts and prints on the screen the number of occurrences of each distinct word (refer to Exercise 1) in the file "article.txt."
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
    for (std::map<std::string, int>::iterator it = mapOfWords.begin(); it != mapOfWords.end(); it++) {
        std::cout << it -> first << " " << it -> second << std::endl;
    }
    std::cout << "\n";
    std::cout << "\n";
    return 0;
}
