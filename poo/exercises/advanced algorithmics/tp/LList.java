public class LList<T> {
    private Node<T> head;
    private int size;

    // liste vide
    public LList() {
        this.head = null;
        size = 0;
    }

    // liste avec une element
    public LList(T elem) {
        this.head = new Node<T>(elem);
        size++;
    }

    // liste a partir d’une tete de liste et d’une queue de liste
    public LList(T head, LList<T> tail) {
        this.head = new Node<T>(head, tail.getHead());
    }

    public Node<T> getHead() {
        return this.head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    // ajoute en queue de liste
    public void add(T elem) {
        if (this.head == null) {
            head = new Node<T>(elem);
        } else {
            Node<T> p = head;
            while (p.hasNext()) {
                p = p.getNext();
            }
            p.setNext(new Node<T>(elem));
        }
        size++;
    }

    // ajoute en premier
    public void addFirst(T elem) {
        this.head = new Node<T>(elem, this.head);
        size++;
    }

    public void add(int index, T elem) {
        if (index == 0) {
            head = new Node<T>(elem, head);
        } else {
            int i = 0;
            Node<T> p = head;
            while (i < index - 1 && p.hasNext()) {
                p = p.getNext();
                i++;
            }
            Node<T> newNode = new Node<T>(elem, p.getNext());
            p.setNext(newNode);
            size++;
        }
    }

    public void addAll(LList<T> l) {
        if (this.isEmpty()) {
            this.setHead(l.getHead());
            this.size += l.getSize();
        } else {
            Node<T> p = head;
            while (p.hasNext()) {
                p = p.getNext();
            }
            p.setNext(l.head);
            this.size += l.getSize();
        }
    }

    public void addAllRec(LList<T> l) {
        if (this.isEmpty()) {
            this.setHead(l.getHead());
        } else {
            this.setHead(addNodeRec(this.getHead(), l.getHead()));
            this.size += l.getSize();
        }
    }

    private Node<T> addNodeRec(Node<T> p, Node<T> q) {
        if (p == null) {
            return q;
        } else {
            return new Node<T>(p.getData(), addNodeRec(p.getNext(), q));
        }
    }

    /*
     * methode a moi public void addAllRec(LList<T> l) { if(this.isEmpty()) {
     * this.setHead(l.getHead()); } else { Node<T> p = addNodeRec(head);
     * p.setNext(l.head); } }
     *
     * private Node<T> addNodeRec(Node<T> p) { if (!p.hasNext()) { return p; } else
     * { return addNodeRec(p.getNext()); } }
     */

    public void addAllIndex(LList<T> l, int index) {
        if (index > this.size)
            System.out.println("error index of bounds");
        if (this.isEmpty())
            this.setHead(l.getHead());
        else {
            if (index == 0) {
                l.addAll(this);
                this.setHead(l.getHead());
                this.size += l.getSize();
            } else {
                int i = 0;
                Node<T> p = this.getHead();
                while (i < index - 1 && p.hasNext()) {
                    p = p.getNext();
                    i++;
                }
                Node<T> temp = p.getNext();
                p.setNext(l.getHead());
                while (p.hasNext()) {
                    p = p.getNext();
                }
                p.setNext(temp);
                this.size += l.getSize();
            }
        }
    }

    public void addAllIndexRec(LList<T> l, int index) {
        if (index > this.size)
            System.out.println("error index of bounds");
        else {
            this.setHead(addAllIndexNodeRec(this.getHead(), l.getHead(), index));
            this.size += l.getSize();
        }
    }

    private Node<T> addAllIndexNodeRec(Node<T> p, Node<T> q, int index) {
        if (p == null) {
            return q;
        } else {
            if (index == 0)
                return addNodeRec(p, q);
            else {
                index -= 1;
                return new Node<T>(p.getData(), addAllIndexNodeRec(p.getNext(), q, index));
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void incSize() {
        this.size++;
    }

    public void decSize() {
        this.size--;
    }

    public String toString() {
        // System.out.println("list size : "+this.size);
        String res = "";
        Node<T> p = head;
        while (p != null) {
            res += p.getData().toString();
            if (p.hasNext()) {
                res += ", ";
            }
            p = p.getNext();
        }
        return "(" + res + ")";
    }

    public int size() {
        if (head == null)
            return 0;
        else {
            int i = 1;
            Node<T> p = head;
            while (p.hasNext()) {
                p = p.getNext();
                i++;
            }
            return i;
        }
    }

    public int nbNodes(Node<T> node) {
        if (node == null)
            return 0;
        else
            return 1 + nbNodes(node.getNext());
    }

    // enleve et retourne le premier element
    public T pop() {
        if (isEmpty())
            return null;
        else {
            T elem = head.getData();
            head = head.getNext();
            size--;
            return elem;
        }
    }

    // enleve et retourne le dernier element
    public T dequeue() {
        if (isEmpty()) {
            return null;
        } else if (!head.hasNext()) {
            T elem = head.getData();
            head = null;
            size--;
            return elem;
        } else {
            Node<T> p = head;
            Node<T> q = p.getNext();
            while (q.hasNext()) {
                p = q;
                q = q.getNext();
            }
            p.setNext(null);
            size--;
            return q.getData();
        }
    }

    public void remove(int index) {
        if (index == 0 && !isEmpty()) {
            head = head.getNext();
        } else if (index <= size && !isEmpty()) {
            int i = 0;
            Node<T> p = head;
            Node<T> q = p.getNext();
            while (i < index - 1 && p.hasNext()) {
                p = q;
                q = q.getNext();
                i++;
            }
            if (q.hasNext()) {
                p.setNext(q.getNext());
            } else {
                p.setNext(null);
            }
        }
        size--;
    }

    public void removeRec(int index) {
        if (!isEmpty() && index == 0)
            head = head.getNext();
        else if (!isEmpty() && index <= size) {
            head = nodeRemove(index, head);
            size--;
        }
    }

    private Node<T> nodeRemove(int index, Node<T> l) {
        if (l == null)
            return null;
        if (index == 0)
            return l.getNext();
        else
            return new Node<T>(l.getData(), nodeRemove(index - 1, l.getNext()));
    }

    public int nbOccurrence(T elem) {
        int nb = 0;
        if (isEmpty()) {
            return -1;
        } else {
            Node<T> p = head;
            while (p != null) {
                if (p.getData().equals(elem))
                    nb++;
                p = p.getNext();
            }
            return nb;
        }
    }

    public int nbOccurrencesRec(T elem) {
        if (isEmpty()) {
            return -1;
        } else {
            return nbOccurrencesR(head, elem);
        }
    }

    private int nbOccurrencesR(Node<T> p, T elem) {
        if (p != null) {
            if (p.getData().equals(elem))
                return 1 + nbOccurrencesR(p.getNext(), elem);
            else
                return nbOccurrencesR(p.getNext(), elem);
        } else {
            return 0;
        }
    }

    public boolean contains(T elem) {
        if (isEmpty()) {
            return false;
        } else {
            Node<T> p = head;
            while (p != null) {
                if (p.getData().equals(elem))
                    return true;
                p = p.getNext();
            }
            return false;
        }
    }

    public int indexOf(T elem) {
        if (isEmpty()) {
            return -1;
        } else {
            Node<T> p = head;
            int i = 0;
            while (p != null) {
                if (p.getData().equals(elem))
                    return i;
                p = p.getNext();
                i++;
            }
            return -1;
        }
    }

    public boolean isStammered() {
        boolean test = true;
        Node<T> p = this.getHead();
        while (p != null && test) {
            test = p.hasNext() && p.getData().equals(p.getNext().getData());
            if (p.hasNext())
                p = p.getNext().getNext();
        }
        return test;
    }

    public boolean isStammeredRec() {
        if (this.getHead() == null)
            return true;
        else
            return isStammeredNodeRec(this.getHead());
    }

    private boolean isStammeredNodeRec(Node<T> l) {
        if (l == null)
            return true;
        else {
            if (!l.hasNext())
                return false;
            else
                return l.getData().equals(l.getNext().getData()) && isStammeredNodeRec(l.getNext().getNext());
        }
    }

    public void stammer() {
        if (!this.isEmpty()) {
            Node<T> p = this.getHead();
            while (p != null) {
                p.setNext(new Node<T>(p.getData(), p.getNext()));
                p = p.getNext().getNext();
            }
            this.size *= 2;
        }
    }

    public void stammerRec() {
        if (!this.isEmpty()) {
            stammerNodeRec(this.getHead());
            this.size *= 2;
        }
    }

    private void stammerNodeRec(Node<T> l) {
        if (l != null) {
            l.setNext(new Node<T>(l.getData(), l.getNext()));
            stammerNodeRec(l.getNext().getNext());
        }
    }

    public void removeStammer() {
        if (isStammered()) {
            for (int i = 0; i < this.size; i++) {
                if (i % 2 == 0) {
                    int j = 0;
                    Node<T> p = head;
                    Node<T> q = p.getNext();
                    while (j < i - 1 && p.hasNext()) {
                        p = q;
                        q = q.getNext();
                        j++;
                    }
                    if (q.hasNext()) {
                        p.setNext(q.getNext());
                    } else {
                        p.setNext(null);
                    }
                }
            }
            this.size /= 2;
        }
    }

    public void removeStammerRec() {
        this.setHead(removeStammerRecNode(this.getHead()));
    }

    private Node<T> removeStammerRecNode(Node<T> L) {
        if (L == null)
            return null;
        else
            return new Node<T>(L.getData(), removeStammerRecNode(L.getNext().getNext()));
    }

    public <U> Assoc zip(LList<U> l) {
        Node<Couple> head = null;
        if (!this.isEmpty()) {
            Node<T> p = this.getHead();
            Node<U> q = l.getHead();
            head = new Node<Couple>(new Couple(p.getData(), q.getData()));
            Node<Couple> w = head;
            while (p.hasNext()) {
                p = p.getNext();
                q = q.getNext();
                Couple tmp = new Couple(p.getData(), q.getData());
                w.setNext(new Node<Couple>(tmp));
                w = w.getNext();
            }
        }
        Assoc a = new Assoc();
        a.setHead(head);
        return a;
    }

    // Define a filter routine for LList<T>
    public LList<T> filter(Filter<T> f) {
        Node<T> p = this.getHead();
        Node<T> w = null;
        Node<T> res = null;
        LList<T> Lres = new LList<T>();
        while (p != null && !f.filter(p.getData())) {
            p = p.getNext();
        }
        // if there is some elements left
        if (p != null) {
            res = new Node<T>(p.getData());
            w = res;
            while (p.hasNext()) {
                p = p.getNext();
                if (f.filter(p.getData())) {
                    w.setNext(new Node<T>(p.getData()));
                    w = w.getNext();
                }
            }
            Lres.setHead(res);
        }
        return Lres;
    }

    // Define a map routine for LList<T>
    public <K> LList<K> map(Mapper<T, K> m) {
        LList<K> Lres = new LList<K>();
        Node<T> p = this.getHead();
        if (p == null)
            return Lres;
        else {
            Node<K> res = new Node<K>(m.mapTo(p.getData()));
            Node<K> w = res;
            p = p.getNext();
            while (p != null) {
                w.setNext(new Node<K>(m.mapTo(p.getData())));
                w = w.getNext();
                p = p.getNext();
            }
            Lres.setHead(res);
            return Lres;
        }
    }

    // Define a reduce routine for LList<T>
    public <K> K reduceRec(Reducer<T, K> r) {
        return reduceNode(r, this.getHead());
    }

    private <K> K reduceNode(Reducer<T, K> r, Node<T> L) {
        if (L == null)
            return r.getBase();
        else
            return r.reduce(L.getData(), reduceNode(r, L.getNext()));
    }

    public <K> K reduce(Reducer<T, K> r) {
        K res = r.getBase();
        if (this.getHead() == null)
            return res;
        // create a reverse order list
        Node<T> reverse = null;
        Node<T> p = this.getHead();
        while (p != null) {
            reverse = new Node<T>(p.getData(), reverse);
            p = p.getNext();
        }
        while (reverse != null) {
            res = r.reduce(reverse.getData(), res);
            reverse = reverse.getNext();
        }
        return res;
    }

}