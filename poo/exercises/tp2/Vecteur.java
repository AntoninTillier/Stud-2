
public class Vecteur {

    private double x;
    private double y;
    private String nom;
    private static final double Epsilon = 1 / 10000000.;

    public Vecteur(double a, double b, String s) {
        x = a;
        y = b;
        nom = s;
    }

    // vecteur � partir de deux Points
    public Vecteur(Point p1, Point p2) {
        x = p2.getX() - p1.getX();
        y = p2.getY() - p1.getY();
        nom = "vecteur entre deux points";
    }

    public void affiche() {
        System.out.println("nom du vecteur : " + nom + " x : " + x + " y : " + y);

    }

    public Vecteur addition(Vecteur v2) {
        Vecteur v = new Vecteur(x + v2.x, y + v2.y, nom + " " + v2.nom);
        return v;
    }

    public Vecteur multiplication(double k) {
        Vecteur v = new Vecteur(k * x, k * y, k + " " + nom);
        return v;
    }

    public boolean colineaire(Vecteur v2) {
        boolean b = false;
        // il existe k tel que this = k.v1
        if (Math.abs(x / v2.x - y / v2.y) < Epsilon)
            b = true;
        System.out.println((x / v2.x) - (y / v2.y));
        return b;
    }

    public boolean perpendiculaire(Vecteur v2) {
        boolean b = false;
        if (Math.abs(x * v2.x + y * v2.y) < Epsilon) //produit scalaire nul
            b = true;
        return b;
    }

    public double norme() {
        double resu = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //System.out.println("norme : "+resu);
        return resu;
    }

    public Vecteur rotation(double angle) {
        double a = Math.cos(angle) * x - Math.sin(angle) * y;
        double b = Math.sin(angle) * x + Math.cos(angle) * y;
        String s = "angle " + angle + " " + nom;
        Vecteur v = new Vecteur(a, b, s);
        return v;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
