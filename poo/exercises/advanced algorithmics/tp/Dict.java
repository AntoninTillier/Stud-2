public class Dict<K, V> {
    private LList<K> keys;
    private LList<V> values;
    private int size;

    public Dict() {
        this.keys = new LList<K>();
        this.values = new LList<V>();
        this.setSize(0);
    }

    public Dict(LList<K> keys, LList<V> values) {
        this.keys = keys;
        this.values = values;
        this.setSize(this.keys.getSize());
    }

    public String toString() {
        /*
         * System.out.println("keys size : " + this.keys.getSize());
         * System.out.println("values size : " + this.values.getSize());
         * System.out.println("dict size : " + this.size);
         */
        String res = "";
        Node<K> p = this.keys.getHead();
        Node<V> q = this.values.getHead();
        while (p != null) {
            res += " { " + p.getData().toString() + " , " + q.getData().toString() + "}";
            if (p.hasNext()) {
                res += ", ";
            }
            p = p.getNext();
            q = q.getNext();
        }
        return "[" + res + "]";
    }

    public V get(K key) {
        Node<K> p = this.keys.getHead();
        Node<V> q = this.values.getHead();
        V res = null;
        while (p != null && res == null) {
            if (p.getData().equals(key))
                res = q.getData();
            p = p.getNext();
            q = q.getNext();
        }
        return res;
    }

    public void add(K key, V value) {
        if (keys.isEmpty() && values.isEmpty()) {
            this.keys.setHead(new Node<K>(key));
            this.values.setHead(new Node<V>(value));
            this.keys.setSize(this.keys.getSize() + 1);
            this.values.setSize(this.values.getSize() + 1);
            this.size++;
        } else {
            Node<K> p = this.keys.getHead();
            Node<V> q = this.values.getHead();
            boolean notIn = true;
            while (p.hasNext() && notIn) {
                if (p.getData().equals(key)) {
                    q.setData(value);
                    notIn = false;
                }
                p = p.getNext();
                q = q.getNext();
            }
            if (notIn) {
                p.setNext(new Node<K>(key));
                q.setNext(new Node<V>(value));
                this.keys.setSize(this.keys.getSize() + 1);
                this.values.setSize(this.values.getSize() + 1);
                this.size++;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LList<K> getKeys() {
        return keys;
    }

    public void setKeys(LList<K> keys) {
        this.keys = keys;
    }

    public LList<V> getValues() {
        return values;
    }

    public void setValues(LList<V> values) {
        this.values = values;
    }

}
