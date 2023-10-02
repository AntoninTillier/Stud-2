public class Inventaire extends Dict<String, Double> {

    public Inventaire() {
        super();
    }

    public Inventaire(LList<String> nxtKL, LList<Double> nxtKV) {
        super(nxtKL, nxtKV);
    }

    public void add(String k, Double v) {
        if (v >= 0)
            super.add(k, v);
        else
            System.err.println("valeur négative non autorisé");
    }

    public double getTotal() {
        Node<String> p = this.getKeys().getHead();
        double compt = 0;
        while (p != null) {
            compt += this.get(p.getData());
            p = p.getNext();
        }
        return compt;
    }

    public LList<String> getProductFilter(double priceMax) {
        Node<String> res = null;
        Node<String> w = null;
        Node<String> p = this.getKeys().getHead();
        int count = 0;
        while (p != null) {
            if (this.get(p.getData()) <= priceMax) {
                if (res == null) {
                    res = new Node<String>(p.getData());
                    w = res;
                    count++;
                } else {
                    w.setNext(new Node<String>(p.getData()));
                    w = w.getNext();
                    count++;
                }
            }
            p = p.getNext();
        }
        LList<String> l = new LList<String>();
        l.setHead(res);
        l.setSize(count);
        return l;
    }

    public LList<String> getProductFilterRec(double priceMax) {
        LList<String> res = new LList<String>();
        int count = 0;
        Node<String> hres = getProductFilterNode(this, priceMax);
        res.setHead(hres);
        while (hres != null) {
            count++;
            hres = hres.getNext();
        }
        res.setSize(count);
        return res;
    }

    private Node<String> getProductFilterNode(Inventaire i, double priceMax) {
        if (i.getKeys().isEmpty())
            return null;
        else {
            String fKey = i.getKeys().getHead().getData();
            Node<String> nK = i.getKeys().getHead().getNext();
            LList<String> nKL = new LList<String>();
            nKL.setHead(nK);
            Node<Double> nV = i.getValues().getHead().getNext();
            LList<Double> nVL = new LList<Double>();
            nVL.setHead(nV);
            Inventaire next = new Inventaire(nKL, nVL);
            if (i.get(fKey) <= priceMax) {
                return new Node<String>(fKey, getProductFilterNode(next, priceMax));
            } else {
                return getProductFilterNode(next, priceMax);
            }
        }
    }

    public void mapConvert(double conversionRate) {
        Node<Double> p = this.getValues().getHead();
        while (p != null) {
            double val = p.getData();
            p.setData(val * conversionRate);
            p = p.getNext();
        }
    }

    public void merge(Inventaire i) {
        Node<String> p = i.getKeys().getHead();
        while (p != null) {
            this.add(p.getData(), i.get(p.getData()));
            p = p.getNext();
        }
    }

}