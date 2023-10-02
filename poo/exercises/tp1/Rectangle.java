public class Rectangle {
    Point basGauche;
    Point hautDroite;

    public Rectangle(Point bg, Point hd) {
        basGauche = bg;
        hautDroite = hd;
    }

    public Rectangle(Point bg, Vecteur v) {
        basGauche = bg;
        hautDroite = bg.translater(v);
    }

    public void affiche() {
        System.out.println("Rectangle");
        System.out.print("bas gauche ");
        basGauche.affiche();
        System.out.print("haut Droite ");
        hautDroite.affiche();
    }

    public Point getBasGauche() {
        return basGauche;
    }

    public Point getHautDroite() {
        return hautDroite;
    }

}