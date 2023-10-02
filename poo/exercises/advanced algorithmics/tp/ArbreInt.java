public class ArbreInt extends Arbre<Integer> {

    public ArbreInt(Integer _label) {
        super(_label);
    }

    public ArbreInt(Integer _label, ArbreInt _left, ArbreInt _right) {
        super(_label, _left, _right);
    }

    public void addRand(int val) {
        double x = Math.random();
        if (x < 0.5) {
            if (this.hasLeft())
                ((ArbreInt) this.getLeft()).addRand(val);
            else
                this.setLeft(new ArbreInt(val, null, null));
        } else {
            if (this.hasRight())
                ((ArbreInt) this.getRight()).addRand(val);
            else
                this.setRight(new ArbreInt(val, null, null));

        }
    }

    public void addAlea(int size, int valMax) {
        for (int i = 0; i < size; i++) {
            this.addRand((int) (Math.random() * (valMax)));
        }
    }

    public void addOrder(int val) {
        if (val < this.getLabel()) {
            if (this.hasLeft())
                ((ArbreInt) this.getRight()).addOrder(val);
            else
                this.setLeft(new ArbreInt(val, null, null));
        } else {
            if (this.hasRight())
                ((ArbreInt) this.getRight()).addOrder(val);
            else
                this.setRight(new ArbreInt(val, null, null));
        }
    }

    public int shortestPath() {
        if (isLeaf())
            return 1;
        else {
            int left = (this.hasLeft()) ? ((ArbreInt) this.getLeft()).shortestPath() : 0;
            int right = (this.hasRight()) ? ((ArbreInt) this.getRight()).shortestPath() : 0;
            if (left > 0 && right > 0)
                return 1 + Math.min(left, right);
            else if (left > 0)
                return 1 + left;
            else
                return 1 + right;
        }
    }

    public Node<Integer> shortestPathNodes() {
        if (isLeaf())
            return new Node<Integer>(this.getLabel());
        else {
            Node<Integer> left = (this.hasLeft()) ? ((ArbreInt) this.getLeft()).shortestPathNodes() : null;
            Node<Integer> right = (this.hasRight()) ? ((ArbreInt) this.getRight()).shortestPathNodes() : null;
            if (left == null && right == null)
                return new Node<Integer>(this.getLabel(), null);
            else if (left == null)
                return new Node<Integer>(this.getLabel(), right);
            else if (right == null)
                return new Node<Integer>(this.getLabel(), left);
            return (left.size() <= right.size()) ? new Node<Integer>(this.getLabel(), left)
                    : new Node<Integer>(this.getLabel(), right);
        }
    }

}