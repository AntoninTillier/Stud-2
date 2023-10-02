public class TD5Map extends Assoc {

    public TD5Map() {
        super();
    }

    public LList<String> listeNom() {
        TD5Nom m = new TD5Nom();
        LList<String> res = this.map(m);
        return res;
    }

    public void multipication(Double e) {
        TD5Multi m = new TD5Multi(e);
        LList<Couple> l = this.map(m);
        this.setHead(l.getHead());
    }

}
