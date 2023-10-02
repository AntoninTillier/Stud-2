#ifndef VIEW_H
#define VIEW_H
#include<QWidget>
#include<QtGui>
#include<QPushButton>
#include<QLineEdit>
#include<QString>
#include<QTextEdit>
#include<QSystemTrayIcon>
#include "Agenda.h"

class View : public QWidget {
    Q_OBJECT
public:
    View(const std::string &);
    virtual ~View();
private slots:
    void add();
    void erase();
    void search();
    void edit();
    void whofoc();
    void myaclose();
    void myaopen();
    void about();
private:
    std::string chemin;
    Agenda a_;
    QPushButton *ajouter;
    QPushButton *supprimer;
    QPushButton *modifier;
    QLineEdit *recherche;
    QTextEdit *lAgenda;
    QString date;
    QString heure;
    QString rdv;
    QString currentDateTime;
    bool notif;
    std::string todayRdv;
    QSystemTrayIcon *sticon;
};

#endif // VIEW_H
