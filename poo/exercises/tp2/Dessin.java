
public class Dessin {

    private Forme tab[];

    public Dessin(Forme t[]) {
        tab = t;
    }

    public void affiche() {
        for (int i = 0; i < tab.length; i++)
            tab[i].affiche();
    }

    public int nbFormes() {
        return tab.length;
    }

    public void translater(Vecteur v) {
        for (int i = 0; i < tab.length; i++)
            tab[i].translater(v);
    }

    public void rotation(double angle) {
        for (int i = 0; i < tab.length; i++)
            tab[i].rotation(angle);
    }

    public int nbCercles() {
        int nb = 0;
        for (int i = 0; i < tab.length; i++)
            if (tab[i].getClass().getName() == "Cercle")
                nb++;
        return nb;
    }

}
