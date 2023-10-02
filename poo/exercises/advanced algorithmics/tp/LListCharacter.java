public class LListCharacter extends LList<Character> {

    public LListCharacter() {
        super();
    }

    public LListCharacter(Character elem) {
        super(elem);
    }

    public LListCharacter(Character head, LList<Character> tail) {
        super(head, tail);
    }

    public boolean equals(LListCharacter l) {
        if (this.getSize() == l.getSize()) {
            Node<Character> p = this.getHead();
            Node<Character> q = l.getHead();
            while (p != null) {
                if (p.getData().equals(q.getData())) {
                    p = p.getNext();
                    q = q.getNext();
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void clone(LListCharacter l) {
        if (this.isEmpty()) {
            System.out.println("liste vide");
        } else {
            Node<Character> p = this.getHead();
            while (p != null) {
                l.add(p.getData());
                p = p.getNext();
            }
        }
    }

    public LListCharacter cloneM() {
        Node<Character> p = this.getHead();
        if (p == null)
            return new LListCharacter();
        else {
            LListCharacter l = new LListCharacter(p.getData());
            Node<Character> w = l.getHead();
            while (p.hasNext()) {
                p = p.getNext();
                w.setNext(new Node<Character>(p.getData()));
                w = w.getNext();
            }
            l.setSize(this.size());
            return l;
        }
    }

    public boolean containsAll(LListCharacter l) {
        if (this.isEmpty() || l.isEmpty()) {
            return false;
        } else {
            if (this.size() > l.size() || l.size() > this.size())
                return false;
            Node<Character> p = this.getHead();
            Node<Character> q = l.getHead();
            int c = 0;
            while (q != null) {
                if (p.getData().equals(q.getData())) {
                    p = this.getHead();
                    q = q.getNext();
                    c++;
                } else {
                    p = p.getNext();
                }
            }
            if (c == l.size()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean containsAllRec(LListCharacter l) {
        if (this.isEmpty() || l.isEmpty()) {
            return false;
        } else {
            if (this.size() > l.size() || l.size() > this.size())
                return false;
            Node<Character> p = this.getHead();
            Node<Character> q = l.getHead();
            int c = l.size();
            if (containsAllR(p, q, c))
                return true;
            else
                return false;
        }
    }

    private boolean containsAllR(Node<Character> p, Node<Character> q, int c) {
        if (c == 0) {
            return true;
        } else if (q == null) {
            return false;
        }
        if (p.getData().equals(q.getData())) {
            return containsAllR(this.getHead(), q.getNext(), c - 1);
        } else {
            return containsAllR(p.getNext(), q, c);
        }
    }

    public void reverse() {
        int size = this.getSize();
        char a = this.dequeue();
        Node<Character> newHead = new Node<Character>(a);
        Node<Character> tamp = newHead;
        while (this.getHead() != null) {
            a = this.dequeue();
            newHead.setNext(new Node<Character>(a));
            newHead = newHead.getNext();
        }
        this.setHead(tamp);
        this.setSize(size);
    }

    public void reverseRec() {
        int size = this.getSize();
        char a = this.dequeue();
        Node<Character> n = new Node<Character>(a);
        Node<Character> tamp = n;
        reverseNode(n);
        this.setHead(tamp);
        this.setSize(size);
    }

    private void reverseNode(Node<Character> n) {
        if (this.getHead() != null) {
            char a = this.dequeue();
            n.setNext(new Node<Character>(a));
            reverseNode(n.getNext());
        }
    }

    public boolean palindrome() {
        LListCharacter l = new LListCharacter();
        l = this.cloneM();
        l.reverse();
        if (this.equals(l))
            return true;
        else
            return false;
    }

    public void listAlea(int n) {
        this.setSize(n);
        if (this.isEmpty()) {
            int delta = (int) (Math.random() * 26);
            this.setHead(new Node<Character>((char) ((int) 'A' + delta)));
            n--;
        }
        Node<Character> w = this.getHead();
        while (n > 0) {
            int delta = (int) (Math.random() * 26);
            w.setNext(new Node<Character>((char) ((int) 'A' + delta)));
            w = w.getNext();
            n--;
        }
    }

    public Node<Couple> insertion(Node<Couple> freq, Character elem) {
        if (freq == null)
            return new Node<Couple>(new Couple(elem, 1));
        else {
            if (freq.getData().getFirst().equals(elem)) {
                Couple c = new Couple(freq.getData().getFirst(), (Integer) freq.getData().getSecond() + 1);
                return new Node<Couple>(c, freq.getNext());
            } else {
                return new Node<Couple>(freq.getData(), insertion(freq.getNext(), elem));
            }
        }
    }

    public Assoc frequence() {
        Node<Character> p = this.getHead();
        Node<Couple> res = null;
        while (p != null) {
            res = insertion(res, p.getData());
            p = p.getNext();
        }
        Assoc a = new Assoc();
        a.setHead(res);
        return a;
    }

}