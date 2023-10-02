#include "View.h"
#include<QPushButton>
#include<QHBoxLayout>
#include<QVBoxLayout>
#include<QInputDialog>
#include<QMessageBox>
#include<QSystemTrayIcon>
#include<QApplication>
#include<QDesktopWidget>
#include<QDateTime>
#include<string.h>
#include<QMenu>
#include<QAction>
#include<QShortcut>
#include<QMenuBar>
using namespace std;

View::View(const string & chemin) {
    this -> chemin = chemin;
    QString ic = QString::fromStdString(chemin.substr(0, chemin.length() - 6) + "Resources/application.icns");
    this ->chemin += "agenda.txt";
    a_.load(this -> chemin);
    recherche = new QLineEdit("Recherche : ");
    recherche -> setCursor(Qt::IBeamCursor);
    recherche -> setFocusPolicy(Qt::ClickFocus);
    recherche -> setFixedSize(200,25);

    lAgenda = new QTextEdit;
    lAgenda -> setReadOnly(true);
    lAgenda -> setFont(QFont(nullptr,15, QFont::Bold));
    lAgenda -> setText(QString::fromStdString(a_.afficher()));
    ajouter = new QPushButton("add");
    ajouter -> setCursor(Qt::PointingHandCursor);

    supprimer = new QPushButton("erase");
    supprimer -> setCursor(Qt::PointingHandCursor);

    modifier = new QPushButton("edit");
    modifier -> setCursor(Qt::PointingHandCursor);

    QHBoxLayout *lineLayout = new QHBoxLayout;
    lineLayout -> setAlignment(Qt::AlignRight);
    lineLayout -> addWidget(recherche);

    QGridLayout *boutonsLayout = new QGridLayout;
    boutonsLayout -> setAlignment(Qt::AlignBottom);
    boutonsLayout ->addWidget(ajouter, 0, 0);
    boutonsLayout ->addWidget(supprimer, 0, 1);
    boutonsLayout ->addWidget(modifier, 1, 0, 1, 2);


    QHBoxLayout *agendaLayout = new QHBoxLayout;
    agendaLayout -> setAlignment(Qt::AlignCenter);
    agendaLayout -> addWidget(lAgenda);

    QVBoxLayout *layoutPrincipal = new QVBoxLayout;
    layoutPrincipal -> addLayout(lineLayout);
    layoutPrincipal -> addLayout(agendaLayout);
    layoutPrincipal->addLayout(boutonsLayout);

    setLayout(layoutPrincipal);

    currentDateTime = QDateTime::currentDateTime().toString("dd/MM/yyyy - hh:mm");
    notif = true;

    QMenu* stmenu = new QMenu(this);
    QAction* actTexte1 = new QAction("Add",this);
    QAction* actTexte2 = new QAction("Erase",this);
    QAction* actTexte3 = new QAction("Edit",this);
    QAction* actTexte4 = new QAction("Open",this);
    QAction* actTexte5 = new QAction("About",this);
    stmenu->addAction(actTexte1);
    stmenu->addAction(actTexte2);
    stmenu->addAction(actTexte3);
    stmenu ->addSeparator();
    stmenu->addAction(actTexte4);
    stmenu->addAction(actTexte5);

    sticon = new QSystemTrayIcon(this);
    QIcon icon(ic);
    sticon -> setIcon(icon);
    sticon -> setContextMenu(stmenu);
    sticon -> setVisible(true);

    QShortcut* ctrlw = new QShortcut(QKeySequence("Ctrl+W"), this);

    connect(qApp, SIGNAL(focusChanged (QWidget *, QWidget *)), this, SLOT(whofoc()));
    connect(recherche, SIGNAL(returnPressed()), this, SLOT(search()));
    connect(ajouter, SIGNAL(clicked()), this, SLOT(add()));
    connect(supprimer, SIGNAL(clicked()), this, SLOT(erase()));
    connect(modifier, SIGNAL(clicked()), this, SLOT(edit()));

    connect(actTexte1, SIGNAL(triggered()), this, SLOT(add()));
    connect(actTexte2, SIGNAL(triggered()), this, SLOT(erase()));
    connect(actTexte3, SIGNAL(triggered()), this, SLOT(edit()));
    connect(actTexte4, SIGNAL(triggered()), this, SLOT(myaopen()));
    connect(actTexte5, SIGNAL(triggered()), this, SLOT(about()));
    connect(ctrlw, SIGNAL(activated()), this, SLOT(myaclose()));
}

View::~View() {}

void View::search() {
    this -> setVisible(true);
    string mot = recherche -> text().toStdString();
    if(!recherche -> text().isEmpty()){
        bool v = false;
        lAgenda -> clear();
        lAgenda -> setText(QString::fromStdString(a_.rechMot(mot)));
        int c = 0;
        for (int i = 0; i < recherche -> text().length(); i++) {
            if(recherche -> text()[i] == '/' && recherche -> text()[i - 1].isNumber() && recherche -> text()[i + 1].isNumber()){
                v = true;
                c ++;
            }
            if(c == 2)
                break;
            else
                v = false;
        }
        if(v){
            Date d = mot;
            lAgenda -> clear();
            lAgenda -> setText(QString::fromStdString(a_.rechDate(d)));
            v = false;
        }
        for (int i = 0; i < recherche -> text().length(); i++) {
            if(recherche -> text()[i] == ':' && recherche -> text()[i - 1].isNumber() && recherche -> text()[i + 1].isNumber()){
                v = true;
                break;
            }
        }
        if(v){
            Heure h = mot;
            lAgenda -> clear();
            lAgenda -> setText(QString::fromStdString(a_.rechHeure(h)));
            v = false;
        }
    }
}

void View::add() {
    this -> setVisible(true);
    date = QInputDialog::getText(this, "add", "Entrer votre date (jj/mm/aaaa) : ");
    heure = QInputDialog::getText(this, "add", "Entrer votre heure (hh:mm) : ");
    rdv = QInputDialog::getText(this, "add", "Entrer votre rendez-vous : ");
    bool isd = false, ish = false;
    int c = 0;
    for (int i = 0; i < date.length(); i++) {
        if(date[i] == '/' && date[i - 1].isNumber() && date[i + 1].isNumber()){
            isd = true;
            c ++;
        }
        if(c == 2)
            break;
        else
            isd = false;
    }
    for (int i = 0; i < heure.length(); i++) {
        if(heure[i] == ':' && heure[i - 1].isNumber() && heure[i + 1].isNumber()){
            ish = true;
            break;
        }
    }
    if(!date.isEmpty() && !heure.isEmpty() && !rdv.isEmpty() && isd && ish) {
        Date d = date.toStdString();
        Heure h = heure.toStdString();
        string r = rdv.toStdString();
        a_.add(d,h,r);
        a_.saveAgenda(this -> chemin);
        lAgenda -> setText(QString::fromStdString(a_.afficher()));
    } else
        QMessageBox::critical(this, "add", "Vous avez oubliez un champ !");
}

void View::erase() {
    this -> setVisible(true);
    date = QInputDialog::getText(this, "erase", "Entrer votre date (jj/mm/aaaa) : ");
    heure = QInputDialog::getText(this, "erase", "Entrer votre heure (hh:mm) : ");
    rdv = QInputDialog::getText(this, "erase", "Entrer votre rendez-vous : ");
    bool isd = false, ish = false;
    int c = 0;
    for (int i = 0; i < date.length(); i++) {
        if(date[i] == '/' && date[i - 1].isNumber() && date[i + 1].isNumber()){
            isd = true;
            c ++;
        }
        if(c == 2)
            break;
        else
            isd = false;
    }
    for (int i = 0; i < heure.length(); i++) {
        if(heure[i] == ':' && heure[i - 1].isNumber() && heure[i + 1].isNumber()){
            ish = true;
            break;
        }
    }
    if(!date.isEmpty() && !heure.isEmpty() && !rdv.isEmpty() && isd && ish) {
        Date d = date.toStdString();
        Heure h = heure.toStdString();
        string r = rdv.toStdString();
        a_.erase(d,h,r);
        a_.saveAgenda(this -> chemin);
        lAgenda -> setText(QString::fromStdString(a_.afficher()));
    } else
        QMessageBox::critical(this, "erase", "Vous avez oubliez un champ !");
}

void View::edit() {
    this -> setVisible(true);
    QStringList choix;
    choix << tr("modifier Date") << tr("modifier Heure") << tr("modifier un rendez-vous");
    bool ok;
    QString item = QInputDialog::getItem(this, tr("edit"),tr("Choix"), choix, 0, false, &ok);
    if (ok && !item.isEmpty()) {
        if(item == "modifier Date"){
            date = QInputDialog::getText(this, "edit", "Entrer votre date (jj/mm/aaaa) : ");
            heure = QInputDialog::getText(this, "edit", "Entrer votre heure (hh:mm) : ");
            rdv = QInputDialog::getText(this, "edit", "Entrer votre rendez-vous : ");
            int i = QInputDialog::getInt(this, "edit", "Entrer le nombre de jour écartant le rendez-vous initial à celui finalement prévu : ");
            bool isd = false, ish = false;
            int c = 0;
            for (int i = 0; i < date.length(); i++) {
                if(date[i] == '/' && date[i - 1].isNumber() && date[i + 1].isNumber()){
                    isd = true;
                    c ++;
                }
                if(c == 2)
                    break;
                else
                    isd = false;
            }
            for (int i = 0; i < heure.length(); i++) {
                if(heure[i] == ':' && heure[i - 1].isNumber() && heure[i + 1].isNumber()){
                    ish = true;
                    break;
                }
            }
            if(!date.isEmpty() && !heure.isEmpty() && !rdv.isEmpty()  && isd && ish) {
                Date d = date.toStdString();
                Heure h = heure.toStdString();
                string r = rdv.toStdString();
                a_.modifDate(d,h,r,i);
                a_.saveAgenda(this -> chemin);
                lAgenda -> setText(QString::fromStdString(a_.afficher()));
            } else
                QMessageBox::critical(this, "edit", "Vous avez oubliez un champ !");
        }
        if(item == "modifier Heure"){
            date = QInputDialog::getText(this, "edit", "Entrer votre date (jj/mm/aaaa) : ");
            heure = QInputDialog::getText(this, "edit", "Entrer votre heure (hh:mm) : ");
            int i = QInputDialog::getInt(this, "edit", "Entrer le nombre de jour écartant le rendez-vous initial à celui finalement prévu : ");
            bool isd = false, ish = false;
            int c = 0;
            for (int i = 0; i < date.length(); i++) {
                if(date[i] == '/' && date[i - 1].isNumber() && date[i + 1].isNumber()){
                    isd = true;
                    c ++;
                }
                if(c == 2)
                    break;
                else
                    isd = false;
            }
            for (int i = 0; i < heure.length(); i++) {
                if(heure[i] == ':' && heure[i - 1].isNumber() && heure[i + 1].isNumber()){
                    ish = true;
                    break;
                }
            }
            if(!date.isEmpty() && !heure.isEmpty() && isd && ish) {
                Date d = date.toStdString();
                Heure h = heure.toStdString();
                a_.modifHeure(d,h,i);
                a_.saveAgenda(this -> chemin);
                lAgenda -> setText(QString::fromStdString(a_.afficher()));
            } else
                QMessageBox::critical(this, "edit", "Vous avez oubliez un champ !");
        }
        if(item == "modifier un rendez-vous"){
            date = QInputDialog::getText(this, "edit", "Entrer votre date (jj/mm/aaaa) : ");
            heure = QInputDialog::getText(this, "edit", "Entrer votre heure (hh:mm) : ");
            rdv = QInputDialog::getText(this, "edit", "Entrer votre rendez-vous : ");
            QString i = QInputDialog::getText(this, "edit", "Entrer le nouveau rendez-vous : ");
            bool isd = false, ish = false;
            int c = 0;
            for (int i = 0; i < date.length(); i++) {
                if(date[i] == '/' && date[i - 1].isNumber() && date[i + 1].isNumber()){
                    isd = true;
                    c ++;
                }
                if(c == 2)
                    break;
                else
                    isd = false;
            }
            for (int i = 0; i < heure.length(); i++) {
                if(heure[i] == ':' && heure[i - 1].isNumber() && heure[i + 1].isNumber()){
                    ish = true;
                    break;
                }
            }
            if(!date.isEmpty() && !heure.isEmpty() && !rdv.isEmpty()  && isd && ish) {
                Date d = date.toStdString();
                Heure h = heure.toStdString();
                string r = rdv.toStdString();
                string nr = i.toStdString();
                a_.modifRdv(d,h,r,nr);
                a_.saveAgenda(this -> chemin);
                lAgenda -> setText(QString::fromStdString(a_.afficher()));
            } else
                QMessageBox::critical(this, "edit", "Vous avez oubliez un champ !");
        }
    }
}

void View::whofoc() {
    if(currentDateTime != QDateTime::currentDateTime().toString("dd/MM/yyyy - hh:mm"))
        notif = true;
    currentDateTime = QDateTime::currentDateTime().toString("dd/MM/yyyy - hh:mm");
    Date nd = QDateTime::currentDateTime().toString("dd/MM/yyyy").toStdString();
    todayRdv = a_.rechDate(nd);
    string d = currentDateTime.toStdString();
    QString res;
    if(todayRdv.find(d) != string::npos)
        res = QString::fromStdString(todayRdv.substr(todayRdv.find(d) + d.length() + 3, todayRdv.find("\n") - d.length() - 5));
    if(!res.isEmpty() && notif) {
        sticon->showMessage("MyAgenda", "Now : " + res, QSystemTrayIcon::Information, 30000);
        notif = false;
    }
    if(recherche -> hasFocus())
        recherche -> clear();
    else {
        recherche -> setText("Recherche : ");
        lAgenda -> clear();
        lAgenda -> setText(QString::fromStdString(a_.afficher()));
    }
}

void View::myaclose() {
    this -> setVisible(false);
}

void View::myaopen() {
    this -> setVisible(true);
}

void View::about() {
    this -> setVisible(true);
    QMessageBox::information(this, "About", "Program created by Antonin Tillier and Quentin Serrau.\n\nFor more information read the documentation");
}
