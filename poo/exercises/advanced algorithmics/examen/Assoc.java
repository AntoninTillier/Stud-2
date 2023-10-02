
public class Assoc extends LList<Couple> {

    public Assoc() {
        super();
    }

    public Assoc(Couple couple) {
        super(couple);
    }

    // String output
    public String toString() {
        String res = "";
        Node<Couple> p = this.getHead();

        while (p != null) {
            res += "{" + p.getData().getFirst().toString() + ", " + p.getData().getSecond().toString() + "}";
            if (p.hasNext()) {
                res += ", ";
            }
            p = p.getNext();
        }
        return "[" + res + "]";
    }

    // getters
    public LList<Object> getKeys() {
        Node<Object> head = null;
        Node<Object> w = null;
        Node<Couple> r = this.getHead();
        while (r != null) {
            if (head == null) {
                head = new Node<Object>(r.getData().getFirst());
                w = head;
            } else {
                w.setNext(new Node<Object>(r.getData().getFirst()));
            }
            r = r.getNext();
        }
        LList<Object> L = new LList<Object>();
        L.setHead(head);
        return L;
    }

    public LList<Object> getValues() {
        Node<Object> head = null;
        Node<Object> w = null;
        Node<Couple> r = this.getHead();
        while (r != null) {
            if (head == null) {
                head = new Node<Object>(r.getData().getSecond());
                w = head;
            } else {
                w.setNext(new Node<Object>(r.getData().getSecond()));
            }
            r = r.getNext();
        }
        LList<Object> L = new LList<Object>();
        L.setHead(head);
        return L;
    }

    public int size() {
        return super.size();
    }

    // clear the list of association
    public void clear() {
        this.setHead(null);
    }

    // retrieve a value based on a key
    public Object get(Object key) {
        Node<Couple> p = this.getHead();
        Object res = null;
        while (p != null && res == null) {
            if (p.getData().getFirst().equals(key))
                res = p.getData().getSecond();
            p = p.getNext();
        }
        return res;
    }

    // put a new value in the list
    // behave like a dictionary
    public void put(Object key, Object value) {
        Node<Couple> p = this.getHead();
        Node<Couple> memp = null;

        boolean insert = false;

        while (p != null && !insert) {
            if (p.getData().getFirst().equals(key)) {
                p.getData().setSecond(value);
                insert = true;
            }
            memp = p; // memorize the actual pointer
            p = p.getNext();
        }
        if (!insert) {
            if (memp == null) {
                // empty dictionary
                this.setHead(new Node<Couple>(new Couple(key, value)));
                super.incSize();
            } else {
                // insert after last element of dictionary
                memp.setNext(new Node<Couple>(new Couple(key, value)));
                super.incSize();
            }
        }
    }
}
