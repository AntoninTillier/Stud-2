
public class LListSorting extends LList<Integer> implements Sort<Integer> {

    public LListSorting() {
        super();
    }

    public LListSorting(int n, int m) {
        this.setSize(n);
        int r = 1 + (int) (Math.random() * ((m - 1) + 1));
        this.setHead(new Node<Integer>(r));
        Node<Integer> p = this.getHead();
        int i = 0;
        while (i < n - 1) {
            int nb = p.getData();
            while (r == nb)
                r = 1 + (int) (Math.random() * ((m - 1) + 1));
            p.setNext(new Node<Integer>(r));
            p = p.getNext();
            i++;
        }
    }

    @Override
    public LList<Integer> triInsertion() {
        LListSorting l = new LListSorting();
        Node<Integer> p = this.getHead();
        l.add(p.getData());
        Node<Integer> q = l.getHead();
        while (p != null) {
            p = p.getNext();
            boolean c = false;
            int i = 1;
            while (!c && p != null) {
                if (p.getData() >= q.getData() && i == l.size()) {
                    l.add(p.getData());
                    c = true;
                } else {
                    if (p.getData() < q.getData()) {
                        l.add(i - 1, p.getData());
                        c = true;
                    }
                }
                q = q.getNext();
                i++;
                if (q == null)
                    c = true;
            }
            q = l.getHead();
        }
        return l;
    }

    @Override
    public LList<Integer> triBulles() {
        LListSorting l = new LListSorting();
        Node<Integer> p = this.getHead();
        int max = p.getData();
        while (true) {
            while (p.hasNext()) {
                p = p.getNext();
                if (max < p.getData()) {
                    max = p.getData();
                }
            }
            l.addFirst(max);
            int i = this.indexOf(max);
            this.remove(i);
            if (this.isEmpty())
                break;
            p = this.getHead();
            max = p.getData();
        }
        return l;
    }

    @Override
    public LList<Integer> triFusion() {
        if (this.size() > 1) {
            LListSorting l1 = new LListSorting();
            LListSorting l2 = new LListSorting();
            int cut = this.getSize() / 2;
            Node<Integer> p = this.getHead();
            for (int i = 0; i < cut; i++) {
                l1.add(p.getData());
                p = p.getNext();
            }
            for (int i = cut; i < this.getSize(); i++) {
                l2.add(p.getData());
                p = p.getNext();
            }
            l1 = (LListSorting) l1.triFusion();
            l2 = (LListSorting) l2.triFusion();
            LListSorting res = new LListSorting();
            Node<Integer> p1 = l1.getHead();
            Node<Integer> p2 = l2.getHead();
            while (p1 != null || p2 != null) {
                if (p1 != null && (p2 == null || p1.getData() < p2.getData())) {
                    res.add(p1.getData());
                    p1 = p1.getNext();
                } else {
                    res.add(p2.getData());
                    p2 = p2.getNext();
                }
            }
            return res;
        } else
            return this;
    }

    @Override
    public LList<Integer> triRapide() {
        if (this.size() > 1) {
            LListSorting l1;
            LListSorting l2;
            boolean end = false;
            Node<Integer> cutNode = this.getHead();
            do {
                int cut = cutNode.getData();
                l1 = new LListSorting();
                l2 = new LListSorting();
                l2.add(cut);
                Node<Integer> p = this.getHead();
                while (p != null) {
                    if (!p.equals(cutNode)) {
                        int v = p.getData();
                        if (v < cut)
                            l1.add(v);
                        else
                            l2.add(v);
                    }
                    p = p.getNext();
                }
                if (l1.isEmpty())
                    cutNode = cutNode.getNext();
                else
                    end = true;
            } while (!end && cutNode != null);

            if (cutNode == null)
                return this;
            else {

                l1 = (LListSorting) l1.triRapide();
                l2 = (LListSorting) l2.triRapide();

                Node<Integer> p2 = l2.getHead();

                while (p2 != null) {
                    l1.add(p2.getData());
                    p2 = p2.getNext();
                }
                return l1;
            }
        } else
            return this;

    }

    public static void main(String[] args) {
        System.out.println("TRI PAR INSERTION : \n");
        int c = 0;
        long debut = System.currentTimeMillis();
        while (c < 1000) {
            LListSorting l = new LListSorting(10 + (int) (Math.random()) * ((100 - 10) + 1), 6 + (int) (Math.random()) * ((100 - 6) + 1));
            //System.out.println(l.toString());
            //System.out.println();
            l = (LListSorting) l.triInsertion();
            //System.out.println(l.toString());
            //System.out.println("-----------------------------");
            c++;
        }
        long fin = System.currentTimeMillis();
        double m = (fin - debut);
        System.out.println("Temps pour 1000 calculs : " + m);
        System.out.println("Temps pour 1 calcul en moyenne : " + m / 1000);
        System.out.println("écart-type : " + Math.pow(m / 1000, 0.5));
        System.out.println("-----------------------------");

        System.out.println("TRI A BULLES : \n");
        c = 0;
        debut = System.currentTimeMillis();
        while (c < 1000) {
            LListSorting l = new LListSorting(10 + (int) (Math.random()) * ((100 - 10) + 1), 6 + (int) (Math.random()) * ((100 - 6) + 1));
            //System.out.println(l.toString());
            //System.out.println();
            l = (LListSorting) l.triBulles();
            //System.out.println(l.toString());
            //System.out.println("-----------------------------");
            c++;
        }
        fin = System.currentTimeMillis();
        m = (fin - debut);
        System.out.println("Temps pour 1000 calculs : " + m);
        System.out.println("Temps pour 1 calcul en moyenne : " + m / 1000);
        System.out.println("écart-type : " + Math.pow(m / 1000, 0.5));
        System.out.println("-----------------------------");

        System.out.println("TRI FUSION : \n");
        c = 0;
        debut = System.currentTimeMillis();
        while (c < 1000) {
            LListSorting l = new LListSorting(10 + (int) (Math.random()) * ((100 - 10) + 1), 6 + (int) (Math.random()) * ((100 - 6) + 1));
            //System.out.println(l.toString());
            //System.out.println();
            l = (LListSorting) l.triBulles();
            //System.out.println(l.toString());
            //System.out.println("-----------------------------");
            c++;
        }
        fin = System.currentTimeMillis();
        m = (fin - debut);
        System.out.println("Temps pour 1000 calculs : " + m);
        System.out.println("Temps pour 1 calcul en moyenne : " + m / 1000);
        System.out.println("écart-type : " + Math.pow(m / 1000, 0.5));
        System.out.println("-----------------------------");

        System.out.println("TRI RAPIDE : \n");
        c = 0;
        debut = System.currentTimeMillis();
        while (c < 1000) {
            LListSorting l = new LListSorting(10 + (int) (Math.random()) * ((100 - 10) + 1), 6 + (int) (Math.random()) * ((100 - 6) + 1));
            //System.out.println(l.toString());
            //System.out.println();
            l = (LListSorting) l.triBulles();
            //System.out.println(l.toString());
            //System.out.println("-----------------------------");
            c++;
        }
        fin = System.currentTimeMillis();
        m = (fin - debut);
        System.out.println("Temps pour 1000 calculs : " + m);
        System.out.println("Temps pour 1 calcul en moyenne : " + m / 1000);
        System.out.println("écart-type : " + Math.pow(m / 1000, 0.5));
    }
}