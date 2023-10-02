public class Eratos extends LList12 {

    public Eratos() {
        super();
    }

    public Eratos(int n) {
        super();
        this.setHead(new Node<Integer>(2));
        Node<Integer> p = this.getHead();
        int i = 1;
        while (i < n - 1) {
            p.setNext(new Node<Integer>(2 + i));
            p = p.getNext();
            i++;
        }
    }

    private void removeMultiple(int n) {
        LList<Integer> l = this.filter(new Mutliple(n));
        setHead(l.getHead());
    }

    public void eratosthene() {
        Node<Integer> p = this.getHead();
        while (p != null) {
            removeMultiple(p.getData());
            p = p.getNext();
        }
    }
}