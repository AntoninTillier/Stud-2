
public class Triangle extends Forme {

    private Vecteur v1;
    private Vecteur v2;

    public Triangle(Point p, Vecteur nv1, Vecteur nv2) {
        super(p);
        v1 = nv1;
        v2 = nv2;
    }

    public void rotation(double angle) {
        v1 = v1.rotation(angle);
        v2 = v2.rotation(angle);
    }

    public double perimetre() {
        double resu = v1.norme() + v2.norme();
        Point p1 = getBase().translater(v1);
        Point p2 = getBase().translater(v2);
        Vecteur v = new Vecteur(p1, p2);
        resu += v.norme();
        return resu;
    }

    public void multiplication(double s) {
        v1 = v1.multiplication(s);
        v2 = v2.multiplication(s);
    }

    public void affiche() {
        System.out.println("Triangle :");
        super.affiche();
        System.out.print("Point 1 :");
        Point p1 = getBase().translater(v1);
        p1.affiche();
        System.out.print("Point 2 :");
        Point p2 = getBase().translater(v2);
        p2.affiche();
    }

}
