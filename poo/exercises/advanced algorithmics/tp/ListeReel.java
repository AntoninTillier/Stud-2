public class ListeReel extends LList<Double> {

    public ListeReel() {
        super();
    }

    public double sommeCarre() {
        Carre m = new Carre();
        LList<Double> l = this.map(m);
        Somme r = new Somme();
        double res = l.reduce(r);
        return res;
    }

    public double RsommeCarre() {
        SommeCarre r = new SommeCarre();
        return this.reduce(r);
    }

    public boolean isDec() {
        Decroissant r = new Decroissant();
        return (boolean) this.reduce(r).getSecond();
    }

}