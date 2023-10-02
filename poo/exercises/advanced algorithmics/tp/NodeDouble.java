public class NodeDouble<T> {
    private T value;
    private NodeDouble<T> next;
    private NodeDouble<T> prev;

    public NodeDouble(T elem) {
        this.value = elem;
        this.next = null;
        this.prev = null;
    }

    public NodeDouble(T _val, NodeDouble<T> _prev, NodeDouble<T> _next) {
        this.value = _val;
        this.setNext(_next);
        this.setPrev(_prev);
        if (_prev != null) _prev.setNext(this);
        if (_next != null) _next.setPrev(this);
    }

    public T getData() {
        return this.value;
    }

    public void setData(T elem) {
        this.value = elem;
    }

    public NodeDouble<T> getNext() {
        return this.next;
    }

    public void setNext(NodeDouble<T> _next) {
        this.next = _next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public NodeDouble<T> getPrev() {
        return this.prev;
    }

    public void setPrev(NodeDouble<T> _prev) {
        this.prev = _prev;
    }

    public boolean hasPrev() {
        return this.prev != null;
    }
}