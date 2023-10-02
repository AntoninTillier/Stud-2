public class LList12 extends LList<Integer> {

    public LList12() {
        super();
    }

    public LList12(Integer elem) {
        super(elem);
    }

    public LList12(Integer head, LList<Integer> tail) {
        super(head, tail);
    }

    public int sup() {
        Node<Integer> p = this.getHead();
        int max = p.getData();
        while (p.hasNext()) {
            p = p.getNext();
            if (max < p.getData()) {
                max = p.getData();
            }
        }
        return max;
    }

    public void carre() {
        if (this.isEmpty()) {
            System.out.println("liste vide");
        } else {
            Node<Integer> p = this.getHead();
            while (p != null) {
                p.setData(p.getData() * p.getData());
                p = p.getNext();
            }
        }
    }

    public void carreFin(LList12 l) {
        if (this.isEmpty()) {
            System.out.println("liste vide");
        } else {
            Node<Integer> p = this.getHead();
            while (p != null) {
                l.add(p.getData() * p.getData());
                p = p.getNext();
            }
        }
    }

    public LList12 carreFin() { // prof
        LList12 res = new LList12();
        if (!this.isEmpty()) {
            Node<Integer> p = this.getHead();
            while (p != null) {
                res.add((int) Math.pow(p.getData(), 2));
                p = p.getNext();
            }

            return res;
        } else {
            return null;
        }
    }

    public LList12 carreFinM() { // prof
        if (this.isEmpty())
            return null;
        else {
            LList12 res = null;
            Node<Integer> p = this.getHead();
            Node<Integer> w = null;
            while (p != null) {
                if (res == null) {
                    res = new LList12((int) Math.pow(p.getData(), 2));
                    w = res.getHead();
                } else {
                    w.setNext(new Node<Integer>((int) Math.pow(p.getData(), 2)));
                    w = w.getNext();
                    p = p.getNext();
                }
            }
            return res;
        }
    }

    public int nbPlusGrands(int seuil) {
        int nb = 0;
        if (this.isEmpty()) {
            return -1;
        } else {
            Node<Integer> p = this.getHead();
            while (p != null) {
                if (p.getData() > seuil)
                    nb++;
                p = p.getNext();
            }
            return nb;
        }
    }

    public LList12 listePlusGrands(int seuil) {
        LList12 l = new LList12();
        if (this.isEmpty()) {
            return l;
        } else {
            Node<Integer> p = this.getHead();
            while (p != null) {
                if (p.getData() > seuil)
                    l.add(p.getData());
                p = p.getNext();
            }
            return l;
        }
    }

    public LList12 listePlusGrandsM(int seuil) {
        if (this.isEmpty())
            return null;
        else {
            LList12 l = null;
            Node<Integer> p = this.getHead();
            Node<Integer> w = null;
            while (p != null) {
                if (l == null) {
                    if (p.getData() > seuil) {
                        l = new LList12(p.getData());
                        w = l.getHead();
                    } else {
                        if (p.getData() > seuil) {
                            w.setNext(new Node<Integer>(p.getData()));
                            w = w.getNext();
                        }
                        p = p.getNext();
                    }
                }
            }
            return l;
        }
    }

    public void init0toN(int u0, int r, int n) {
        if (n == 0) {
            this.setHead(new Node<Integer>(u0));
            this.setSize(1);
        } else {
            this.setHead(new Node<Integer>(u0));
            Node<Integer> p = this.getHead();
            while (n != 0) {
                p.setNext(new Node<Integer>(r * p.getData()));
                p = p.getNext();
                n--;
            }
        }
        this.setSize(n);
    }

    public void init0toNRec(int u0, int r, int n) {
        if (n == 0) {
            this.setHead(new Node<Integer>(u0));
            this.setSize(1);
        } else {
            this.setHead(new Node<Integer>(u0));
            Node<Integer> p = this.getHead();
            init0toNodeRec(p, r, n);
            this.setSize(n);
        }
    }

    private Node<Integer> init0toNodeRec(Node<Integer> p, int r, int n) {
        if (n == 0) {
            return p;
        } else {
            p.setNext(new Node<Integer>(r * p.getData()));
            p = p.getNext();
            n--;
            return init0toNodeRec(p, r, n);
        }
    }

    public void reverse() {
        int size = this.getSize();
        int a = this.dequeue();
        Node<Integer> newHead = new Node<Integer>(a);
        Node<Integer> tamp = newHead;
        while (this.getHead() != null) {
            a = this.dequeue();
            newHead.setNext(new Node<Integer>(a));
            newHead = newHead.getNext();
        }
        this.setHead(tamp);
        this.setSize(size);
    }

    public void initNto0(int u0, int r, int n) {
        this.init0toN(u0, r, n);
        this.reverse();
    }

    public void list12Alea(int n, int maxValue) {
        this.setSize(n);
        if (this.isEmpty()) {
            int delta = (int) (Math.random() * maxValue);
            this.setHead(new Node<Integer>(delta + 1));
            n--;
        }
        Node<Integer> w = this.getHead();
        while (n > 0) {
            int delta = (int) (Math.random() * maxValue);
            w.setNext(new Node<Integer>(delta + 1));
            w = w.getNext();
            n--;
        }
    }

    public void interclassement(LList<Integer> l) {
        Node<Integer> p = this.getHead();
        Node<Integer> q = l.getHead();
        Node<Integer> res = null;
        Node<Integer> w = null;
        while (p != null && q != null) {
            if (res == null) {
                if (p.getData() <= q.getData()) {
                    res = new Node<Integer>(p.getData());
                    w = res;
                    p = p.getNext();
                } else {
                    res = new Node<Integer>(q.getData());
                    w = res;
                    q = q.getNext();
                }
            } else {
                if (p.getData() <= q.getData()) {
                    w.setNext(new Node<Integer>(p.getData()));
                    p = p.getNext();
                } else {
                    w.setNext(new Node<Integer>(q.getData()));
                    q = q.getNext();
                }
                w = w.getNext();
            }
        }
        if (p != null)
            w.setNext(p);
        else
            w.setNext(q);
        this.setHead(res);
    }

    public boolean croissante() {
        if (this.isEmpty()) {
            return true;
        } else {
            int count = 1;
            Node<Integer> p = this.getHead();
            Node<Integer> q = p.getNext();
            while (q != null) {
                if (p.getData() < q.getData())
                    count++;
                p = p.getNext();
                q = q.getNext();
            }
            if (count == this.size())
                return true;
            else
                return false;
        }
    }

    public boolean croissanteRec() {
        return croissanteNodeRec(this.getHead());
    }

    private boolean croissanteNodeRec(Node<Integer> p) {
        if (p == null || !p.hasNext())
            return true;
        else
            return p.getData() < p.getNext().getData() && croissanteNodeRec(p.getNext());
    }

}