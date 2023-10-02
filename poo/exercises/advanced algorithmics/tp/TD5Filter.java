public class TD5Filter extends LList12 {

    public TD5Filter() {
        super();
    }

    public void moinsOccurrences(Integer e) {
        TD5Occ f = new TD5Occ(e);
        LList<Integer> res = this.filter(f);
        this.setHead(res.getHead());
    }

    public void plusGrand(Integer seuil) {
        TD5G f = new TD5G(seuil);
        LList<Integer> res = this.filter(f);
        this.setHead(res.getHead());
    }

}