public class Polyligne {
    private Point[] tab;

    public Polyligne(Point[] points) {
        tab = points;
    }

    public void affiche() {
        for (int i = 0; i < tab.length; i++)
            tab[i].affiche();
    }

    public int nbPoints() {
        return tab.length;
    }

    public Polyligne translater(Vecteur v) {
        Point[] newtab = new Point[tab.length];
        for (int i = 0; i < tab.length; i++)
            newtab[i] = tab[i].translater(v);
        Polyligne resu = new Polyligne(newtab);
        return resu;
    }

    public boolean polygone() {
        return tab[0].egale(tab[tab.length - 1]);
    }

    public double longueur() {
        double resu = 0;
        for (int i = 0; i < tab.length - 1; i++)
            resu += tab[i].distance(tab[i + 1]);
        return resu;
    }

    public void surechantillonage() {
        Point[] newtab = new Point[(tab.length * 2) - 1];
        int posi = 0;
        for (int i = 0; i < tab.length - 1; i++) // pour tout les couples i i+1
        {
            newtab[posi] = tab[i]; // ajout du point
            posi++;
            newtab[posi] = tab[i].moyen(tab[i + 1]);
            posi++;
        }
        newtab[posi] = tab[tab.length - 1]; //ajout du dernier
        tab = newtab; // changement du tableau
    }

    public Rectangle rectangleEnglobant() {
        double minx, miny, maxx, maxy;
        // initialisation à l'aide des X et Y du premier point de tab
        minx = tab[0].getX();
        maxx = tab[0].getX();
        miny = tab[0].getY();
        maxy = tab[0].getY();
        // recherche des max et des min à l'aide des autres points
        for (int i = 1; i < tab.length; i++) {
            if (tab[i].getX() < minx)
                minx = tab[i].getX();
            if (tab[i].getX() > maxx)
                maxx = tab[i].getX();
            if (tab[i].getY() < miny)
                miny = tab[i].getY();
            if (tab[i].getY() > maxy)
                maxy = tab[i].getY();
        }
        // création des deux points
        Point bg = new Point(minx, miny);
        Point hd = new Point(maxx, maxy);
        // création du rectangle
        Rectangle resu = new Rectangle(bg, hd);
        return resu;
    }


}
