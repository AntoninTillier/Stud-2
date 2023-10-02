public class ABR<T extends Comparable<T>> extends Arbre<T> {

    public ABR(T _label) {
        super(_label);
    }

    public ABR(T _label, ABR<T> _left, ABR<T> _right) {
        super(_label, _left, _right);
    }

    public boolean contains(T elem) {
        if (isLeaf())
            return this.getLabel().compareTo(elem) == 0;
        else {
            boolean found = this.getLabel().compareTo(elem) == 0;
            if (!found) {
                if (this.getLabel().compareTo(elem) > 0 && this.hasLeft()) {
                    found = ((ABR<T>) this.getLeft()).contains(elem);
                } else if (this.getLabel().compareTo(elem) < 0 && this.hasRight()) {
                    found = ((ABR<T>) this.getRight()).contains(elem);
                } else
                    found = false;
            }
            return found;
        }
    }

    public void addLeaf(T elem) {
        if (this.getLabel().compareTo(elem) > 0) {
            if (this.hasLeft()) {
                ((ABR<T>) this.getLeft()).addLeaf(elem);
            } else
                this.setLeft(new ABR<T>(elem));
        } else if (this.getLabel().compareTo(elem) < 0) {
            if (this.hasRight()) {
                ((ABR<T>) this.getRight()).addLeaf(elem);
            } else
                this.setRight(new ABR<T>(elem));
        }
    }


    public ABR<T> leftRotation() {
        ABR<T> pivot = (ABR<T>) this.getRight();
        this.setRight(pivot.getLeft());
        pivot.setRight(this);
        return pivot;
    }

    public ABR<T> rightRotation() {
        ABR<T> pivot = (ABR<T>) this.getLeft();
        this.setLeft(pivot.getRight());
        pivot.setRight(this);
        return pivot;
    }
}