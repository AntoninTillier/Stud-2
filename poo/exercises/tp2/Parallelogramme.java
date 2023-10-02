
public class Parallelogramme extends Forme {
    private static final double E = 1. / 1000000;
    private Vecteur v1;
    private Vecteur v2;

    public Parallelogramme(Point p, Vecteur nv1, Vecteur nv2) {
        super(p);
        v1 = nv1;
        v2 = nv2;
    }

    public void rotation(double angle) {
        v1 = v1.rotation(angle);
        v2 = v2.rotation(angle);
    }

    public double perimetre() {
        return v1.norme() * 2 + v2.norme() * 2;
    }

    public void multiplication(double s) {
        v1 = v1.multiplication(s);
        v2 = v2.multiplication(s);
    }

    public boolean rectangle() {
        if (v1.perpendiculaire(v2))
            return true;
        else
            return false;
    }

    public boolean losange() {

        if (Math.abs(v1.norme() - v2.norme()) < E) // test d'égalité sur des doubles
            return true;
        else
            return false;
    }

    // un carré est un rectangle et un losange
    public boolean carre() {
        return losange() && rectangle();
    }


    public void affiche() {
        System.out.println("Parallelogramme :");
        super.affiche();
        System.out.print("Point 1 :");
        Point p2 = getBase().translater(v1);
        p2.affiche();
        System.out.print("Point 2 :");
        Point p3 = p2.translater(v2);
        p3.affiche();
        System.out.print("Point 3 :");
        Point p4 = getBase().translater(v2);
        p4.affiche();
    }

}
