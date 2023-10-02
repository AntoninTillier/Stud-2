public class LListCirc<T> extends LList<T> {

    public LListCirc() {
        super();
    }

    public LListCirc(T elem) {
        super(elem);
        this.getHead().setNext(this.getHead());
    }

    public LListCirc(T head, LListCirc<T> tail) {
        super(head, tail);
        Node<T> p = this.getHead();
        while (p.getNext() != tail.getHead()) {
            p = p.getNext();
        }
        p.setNext(this.getHead());
    }

    public LListCirc(LList<T> l) {
        if (!l.isEmpty()) {
            Node<T> p = l.getHead();
            Node<T> w = null;
            Node<T> hres = null;
            while (p.hasNext()) {
                if (this.getHead() == null) {
                    Node<T> c = new Node<T>(p.getData());
                    w = c;
                    hres = w;
                } else {
                    w.setNext(new Node<T>(p.getData()));
                    w = w.getNext();
                }
                p = p.getNext();
            }
            w.setNext(hres);
            this.setHead(hres);
        }
    }

    public void setHead(Node<T> newHead) {
        super.setHead(newHead);
        Node<T> p = this.getHead();
        while (p.hasNext() && p.getNext() != newHead)
            p = p.getNext();
        p.setNext(newHead);
    }

    public void add(T elem) {
        if (this.getHead() == null) {
            this.setHead(new Node<T>(elem));
        } else {
            Node<T> p = getHead();
            while (p.getNext() != this.getHead()) {
                p = p.getNext();
            }
            p.setNext(new Node<T>(elem, this.getHead()));
        }
        super.incSize();
    }

    public void push(T elem) {
        if (this.getHead() == null) {
            this.setHead(new Node<T>(elem));
        }
        Node<T> tmp = new Node<T>(elem);
        Node<T> p = this.getHead();
        while (p.getNext() != this.getHead())
            p = p.getNext();
        p.setNext(tmp);
        tmp.setNext(this.getHead());
        this.setHead(tmp);
        this.incSize();
    }

    public T pop() {
        if (this.isEmpty())
            return null;
        else if (!this.getHead().hasNext()) {
            this.setHead(null);
            super.decSize();
            return this.getHead().getData();
        } else {
            Node<T> p = this.getHead();
            while (p.getNext() != this.getHead()) {
                p = p.getNext();
            }
            T elem = this.getHead().getData();
            Node<T> head = p.getNext().getNext();
            this.setHead(head);
            p.setNext(head);
            this.decSize();
            return elem;
        }
    }

    public void add(int index, T elem) {
        if (index == 0) {
            this.push(elem);
        } else {
            int i = 0;
            Node<T> p = this.getHead();
            while (i < index - 1 && p.getNext() != this.getHead()) {
                p = p.getNext();
                i++;
            }
            Node<T> after = new Node<T>(elem, p.getNext());
            p.setNext(after);
        }
        this.incSize();
    }

    public void remove(int index) {
        if (index < this.size()) {
            if (index == 0) {
                this.pop();
            } else {
                int i = 0;
                Node<T> p = this.getHead();
                while (i < index - 1) {
                    i++;
                    p = p.getNext();
                }
                Node<T> after = p.getNext().getNext();
                p.setNext(after);
            }
            this.decSize();
        }
    }

    public void enqueue(T elem) {
        if (this.isEmpty()) {
            Node<T> tmp = new Node<T>(elem);
            this.setHead(tmp);
            tmp.setNext(tmp);
        } else {
            Node<T> p = this.getHead();
            while (p.getNext() != this.getHead())
                p = p.getNext();
            p.setNext(new Node<T>(elem, this.getHead()));
        }
        this.incSize();
    }

    public T dequeue() {
        if (this.isEmpty())
            return null;
        else if (this.getHead().getNext() == this.getHead()) {
            T elem = this.getHead().getData();
            this.setHead(null);
            this.decSize();
            return elem;
        } else {
            Node<T> p = this.getHead();
            Node<T> q = p.getNext();
            while (q.getNext() != this.getHead()) {
                p = q;
                q = q.getNext();
            }
            p.setNext(this.getHead());
            this.decSize();
            return q.getData();
        }
    }

    public void fusion(LListCirc<T> l) {
        Node<T> p = this.getHead();
        while (p.getData() != this.getHead()) {
            p = p.getNext();
        }
        p.setNext(l.getHead());
        p = l.getHead();
        while (p.getNext() != l.getHead()) {
            p = p.getNext();
        }
        p.setNext(this.getHead());
    }

    public String toString() {
        String res = "";
        if (this.getHead() == null)
            return "()";
        else {
            Node<T> p = this.getHead();
            do {
                res += p.getData().toString();
                if (p.getNext() != this.getHead()) {
                    res += ", ";
                }
                p = p.getNext();
            } while (p != this.getHead());
            return "(" + res + ")";
        }
    }
}
