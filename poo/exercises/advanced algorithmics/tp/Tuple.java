public class Tuple extends LList<Object> {
    private int t;

    // "empty" tuple
    public Tuple(int _t) {
        super();
        this.t = _t;
    }

    // initialize a new tuple from provided values
    public Tuple(Object[] values) {
        super();
        this.t = values.length;
        Node<Object> head = new Node<Object>(values[0]);
        Node<Object> p = head;
        for (int i = 1; i < values.length; i++) {
            p.setNext(new Node<Object>(values[i]));
            p = p.getNext();
        }
        this.setHead(head);
    }

    // add in the end
    public void add(Object elem) {
        if (this.size() < t)
            super.add(elem);
    }

    // add in the beginning
    public void push(Object elem) {
        if (this.size() < t)
            super.addFirst(elem);
    }

    // add at a specified index
    public void add(int index, Object elem) {
        if (this.size() < t)
            super.add(index, elem);
    }

}
