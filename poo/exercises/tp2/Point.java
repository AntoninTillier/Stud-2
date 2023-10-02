
public class Point {
    private double x;
    private double y;
    private static final double Epsilon = 1 / 10000000.;

    public Point(double abs, double ord) {
        x = abs;
        y = ord;
    }

    public void affiche() {
        System.out.println("x " + x + " y " + y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean egale(Point p2) {
        if ((Math.abs(x - p2.x) < Epsilon) && (Math.abs(y - p2.y) < Epsilon))
            return true;
        else
            return false;
    }

    public Point translater(Vecteur v) {
        Point p = new Point(x + v.getX(), y + v.getY());
        return p;
    }


    public double distance(Point p2) {
        return Math.sqrt(Math.pow(x - p2.x, 2) + Math.pow(y - p2.y, 2));
    }

    public Point moyen(Point p2) {
        double xx = (x + p2.x) / 2;
        double yy = (y + p2.y) / 2;
        Point pointMoyen = new Point(xx, yy);
        return pointMoyen;
    }


}
