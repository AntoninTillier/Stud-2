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

    // vecteur à partir de deux Points
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
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Vecteur rotation(double angle) {
        double a = Math.cos(angle) * x - Math.sin(angle) * y;
        double b = Math.sin(angle) * x + Math.cos(angle) * y;
        String s = "angle " + angle + " " + nom;
        Vecteur v = new Vecteur(a, b, s);
        return v;
    }

    public static void main(String[] args) {
        Vecteur v1 = new Vecteur(1, 3, "v1");
		/* Vecteur v2 = new Vecteur(3,4,"v2");
		v1.affiche();
		v2.affiche();
		Vecteur v3 = v1.addition(v2);
		v3.affiche();
		v3=v3.multiplication(2.f);
		v3.affiche();
		v1 = v1.rotation(Math.PI/2);
		v1.affiche();
		System.out.println(" norme de "+v1.getNom()+" "+v1.norme());
		
		v1 = v1.multiplication(0.45678765);
		Vecteur v4 = v1.multiplication(2.45678765);
		v4.affiche();
		if (v1.colineaire(v4))
			System.out.println("v1 et v4 sont colinéaire");
		else
			System.out.println("v1 et v4 sont NON colinéaire");
		
		v4 = v4.rotation(Math.PI/2);
		if (v1.perpendiculaire(v4))
			System.out.println("v1 et v4 sont perpendiculaire");
		else
			System.out.println("v1 et v4 sont NON perpendiculaire");
			*/

        Point p1 = new Point(1, 1);
        Point p2 = new Point(-2, 3);
        Point p3 = new Point(10, -2);
        //p2 = p2.translater(v4);
        //p2.affiche();


        Vecteur v5 = new Vecteur(p1, p2);
        v5.affiche();
        Point pm = p1.moyen(p2);
        pm.affiche();
        System.out.println("distance : " + p1.distance(p2));


        // polyligne
        Point[] t = {p1, p2, p3};
        Polyligne pl = new Polyligne(t);
        pl.affiche();
        System.out.println("nb points : " + pl.nbPoints());
        v1.affiche();
        pl = pl.translater(v1);
        pl.affiche();
        if (pl.polygone())
            System.out.println("c'est la limite d'un polygone");
        else
            System.out.println("ce n'est pas la limite d'un polygone");
        Point[] t2 = {p1, p2, p3, p1};
        Polyligne pl2 = new Polyligne(t2);
        if (pl2.polygone())
            System.out.println("c'est la limite d'un polygone");
        else
            System.out.println("ce n'est pas la limite d'un polygone");

        System.out.println("longueur de la polyligne " + pl.longueur());
        System.out.println("longueur de la polyligne " + pl2.longueur());

        pl.affiche();
        System.out.println("longueur de la polyligne " + pl.longueur());
        System.out.println("nb points : " + pl.nbPoints());
        pl.surechantillonage();
        pl.affiche();
        System.out.println("longueur de la polyligne " + pl.longueur());
        System.out.println("nb points : " + pl.nbPoints());

        Rectangle rec1 = new Rectangle(p1, p2);
        rec1.affiche();
        Rectangle rec2 = new Rectangle(p1, v1);
        rec2.affiche();

        if (pm.in(rec1))
            System.out.println("à l'intérieur");
        else
            System.out.println("à l'extérieur");
        Point pp = new Point(10, 10);
        if (pp.in(rec1))
            System.out.println("à l'intérieur");
        else
            System.out.println("à l'extérieur");

        // rectangle englobant
        Rectangle re;
        pl.affiche();
        re = pl.rectangleEnglobant();
        System.out.println("rectangle englobant");
        re.affiche();

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
