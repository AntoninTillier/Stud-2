public class InventaireAssoc extends Assoc {

    public InventaireAssoc() {
        super();
    }

    public InventaireAssoc(Couple couple) {
        super(couple);
    }

    public void add(Couple c) {
        if ((Double) c.getSecond() >= 0)
            super.add(c);
        else
            System.err.println("valeur négative non autorisée");
    }

    public double getTotal() {
        Node<Couple> p = this.getHead();
        double res = 0;
        while (p != null) {
            res += (Double) p.getData().getSecond();
            p = p.getNext();
        }
        return res;
    }

    public InventaireAssoc referencePrice() {
        InventaireAssoc res = new InventaireAssoc();
        Node<Couple> w = null;
        Node<Couple> p = this.getHead();
        while (p != null) {
            if (res.getHead() == null) {
                res.setHead(new Node<Couple>(new Couple(p.getData().getFirst(), p.getData().getSecond())));
            } else {
                boolean inserted = false;
                w = res.getHead();
                Node<Couple> prev = null;
                while (!inserted && w != null) {
                    if (w.getData().getFirst().equals(p.getData().getFirst())) {
                        w.setData(new Couple(w.getData().getFirst(), (Double) w.getData().getSecond() + (Double) p.getData().getSecond()));
                        inserted = true;
                    }
                    prev = w;
                    w = w.getNext();
                }
                if (!inserted)
                    prev.setNext(new Node<Couple>(new Couple(p.getData().getFirst(), p.getData().getSecond())));
            }
            p = p.getNext();
        }
        return res;
    }

}