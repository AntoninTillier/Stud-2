
public class principale {
    public static void main(String[] args) {
        Vecteur v1 = new Vecteur(1, 1, "v1");
        Vecteur v2 = new Vecteur(3, 4, "v2");
        Vecteur v3 = new Vecteur(-1, 1, "v3");

        Point p1 = new Point(1, 1);
        Point p2 = new Point(-2, 3);
        Point p3 = new Point(0, 0);


        Cercle c1 = new Cercle(p1, 12.6);
		/*c1.affiche();
		System.out.println("périmétre "+c1.perimetre());
		c1.translater(v1);
		c1.affiche();
		c1.rotation(Math.PI/2);
		c1.affiche();
		c1.multiplication(2.);
		c1.affiche();*/


        Triangle t1 = new Triangle(p2, v1, v2);
		/*t1.affiche();
		System.out.println("périmétre "+t1.perimetre());
		t1.translater(v1);
		t1.affiche();
		t1.rotation(Math.PI/2);
		t1.affiche();
		t1.multiplication(2.);
		t1.affiche();*/

        Parallelogramme pa1 = new Parallelogramme(p3, v1, v3);
		/*pa1.affiche();
		System.out.println("périmétre "+pa1.perimetre());
		pa1.translater(v1);
		pa1.affiche();
		pa1.rotation(Math.PI/2);
		pa1.affiche();
		pa1.multiplication(2.);
		pa1.affiche();
		System.out.println("losange : "+pa1.losange());
		System.out.println("retangle : "+pa1.rectangle());
		System.out.println("carre : "+pa1.carre()); */

        Forme t[] = {c1, t1, pa1, c1};
        Dessin d = new Dessin(t);
        d.affiche();

        d.translater(v1);
        d.affiche();
        System.out.println("nb formes : " + d.nbFormes());

        d.rotation(Math.PI / 4);
        d.affiche();

        System.out.println("nb cercles : " + d.nbCercles());


    }
}
