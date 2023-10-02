#include <QApplication>
#include<QtGui>
#include "View.h"
using namespace std;

int main(int argc, char *argv[]) {
    QApplication app(argc, argv);
    string chemin = argv[0];
    chemin = chemin.substr(0,chemin.length() - 8);
    View f(chemin);
    f.showMaximized();
    return app.exec();
}
