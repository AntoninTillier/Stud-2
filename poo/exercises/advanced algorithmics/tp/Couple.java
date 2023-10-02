public class Couple extends Tuple {

    // default constructor
    public Couple(Object v1, Object v2) {
        super(2);
        this.setHead(new Node<Object>(v1, new Node<Object>(v2)));
    }

    public Object getFirst() {
        return this.getHead().getData();
    }

    public Object getSecond() {
        return this.getHead().getNext().getData();
    }

    public void setFirst(Object first) {
        this.getHead().setData(first);
    }

    public void setSecond(Object second) {
        this.getHead().getNext().setData(second);
    }

    @Override
    public boolean equals(Object c) {
        return this.getFirst().equals(((Couple) c).getFirst()) && this.getSecond().equals(((Couple) c).getSecond());
    }

}
