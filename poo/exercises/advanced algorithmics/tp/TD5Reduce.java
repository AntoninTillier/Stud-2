public class TD5Reduce extends Assoc {

    public TD5Reduce() {
        super();
    }

    public double produit() {
        TD5Produit r = new TD5Produit();
        double res = this.reduce(r);
        return res;
    }

    public String nomMin() {
        TD5NomMin r = new TD5NomMin();
        String res = (String) this.reduce(r).getFirst();
        return res;
    }

}
