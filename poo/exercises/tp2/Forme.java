
abstract public class Forme {

    private Point base;

    public Forme(Point p) {
        base = p;
    }

    public void affiche() {
        System.out.print("Point base : ");
        base.affiche();
    }

    public void translater(Vecteur v) {
        base = base.translater(v);
    }

    public abstract void rotation(double angle);

    public abstract double perimetre();

    public abstract void multiplication(double s);

    public Point getBase() {
        return base;
    }

    public void setBase(Point base) {
        this.base = base;
    }

}
