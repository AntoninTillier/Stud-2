
public class Cercle extends Forme {

    private double rayon;

    public Cercle(Point p, double r) {
        super(p);
        rayon = r;
    }

    public void affiche() {
        System.out.println("Cercle :");
        System.out.print("centre : ");
        getBase().affiche();
        System.out.println("rayon :" + rayon);
    }

    public void rotation(double angle) {
        System.out.println("un cercle est inchangé par rotation");

    }

    public double perimetre() {
        return 2 * Math.PI * rayon;
    }


    public void multiplication(double s) {
        rayon *= s;
    }
}
