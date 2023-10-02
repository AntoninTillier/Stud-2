import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arbre<T> {
    private T label;
    private Arbre<T> left;
    private Arbre<T> right;

    public Arbre(T _label) {
        this.label = _label;
        this.left = null;
        this.right = null;
    }

    public Arbre(T _label, Arbre<T> _left, Arbre<T> _right) {
        this.label = _label;
        this.left = _left;
        this.right = _right;
    }

    public boolean isABR() {
        if (this.isLeaf())
            return true;
        else if ((Integer) this.getLabel() < (Integer) getLeft().getLabel())
            return false;
        else if ((Integer) this.getLabel() < (Integer) getLeft().getLabel())
            return false;
        else
            return true && getLeft().isABR() && getRight().isABR();
    }

    public boolean isLeaf() {
        return (this.getLeft() == null && this.getRight() == null);
    }

    public int nbNodes() {
        if (this.isLeaf())
            return 1;
        else {
            int left = (this.hasLeft()) ? this.getLeft().nbNodes() : 0;
            int right = (this.hasRight()) ? this.getRight().nbNodes() : 0;
            return 1 + left + right;
        }
    }

    public int nbLeaf() {
        if (this.isLeaf())
            return 1;
        else {
            int left = (this.hasLeft()) ? this.getLeft().nbLeaf() : 0;
            int right = (this.hasRight()) ? this.getRight().nbLeaf() : 0;
            return left + right;
        }
    }

    public int nbNodesLevel(int k) {
        if (this.isLeaf()) {
            return 1;
        } else {
            if (k == 1) {
                if (this.hasRight() && this.hasLeft()) {
                    return this.getRight().nbNodes() + this.getLeft().nbNodes() + 1;
                } else {
                    if (this.hasRight())
                        return this.getRight().nbNodes() + 1;
                    else
                        return this.getLeft().nbNodes() + 1;
                }
            } else {
                if (this.hasRight() && this.hasLeft()) {
                    return this.getLeft().nbNodesLevel(k - 1) + this.getRight().nbNodesLevel(k - 1);
                } else {
                    if (this.hasRight())
                        return this.getRight().nbNodesLevel(k - 1);
                    else
                        return this.getLeft().nbNodesLevel(k - 1);
                }
            }
        }
    }

    public int nbLeafLevel(int k) {
        if (this.isLeaf()) {
            return 1;
        } else {
            if (k == 1) {
                if (this.hasRight() && this.hasLeft()) {
                    return this.getRight().nbLeaf() + this.getLeft().nbLeaf();
                } else {
                    if (this.hasRight())
                        return this.getRight().nbLeaf();
                    else
                        return this.getLeft().nbLeaf();
                }
            } else {
                if (this.hasRight() && this.hasLeft()) {
                    return this.getLeft().nbLeafLevel(k - 1) + this.getRight().nbLeafLevel(k - 1);
                } else {
                    if (this.hasRight())
                        return this.getRight().nbLeafLevel(k - 1);
                    else
                        return this.getLeft().nbLeafLevel(k - 1);
                }
            }
        }
    }

    public boolean equals(Arbre<T> other) {
        if (other == null)
            return false;
        else {
            return this.label == other.label && this.hasLeft() == other.hasLeft() && this.hasRight() == other.hasRight()
                    && ((this.hasLeft() && other.hasLeft()) ? this.getLeft().equals(other.getLeft()) : true)
                    && ((this.hasRight() && other.hasRight()) ? this.getRight().equals(other.getRight()) : true);
        }
    }

    public int height() {
        if (this.isLeaf())
            return 1;
        else {
            int left = (this.hasLeft()) ? this.getLeft().height() : 0;
            int right = (this.hasRight()) ? this.getRight().height() : 0;
            return 1 + +Math.max(left, right);
        }
    }

    public Node<T> leftBranch() {
        if (!this.hasLeft())
            return new Node<T>(this.getLabel());
        else
            return new Node<T>(this.getLabel(), this.getLeft().leftBranch());
    }

    public Node<T> rightBranch() {
        if (!this.hasRight())
            return new Node<T>(this.getLabel());
        else
            return new Node<T>(this.getLabel(), this.getRight().rightBranch());
    }

    public int strahler() {
        int sl = (this.hasLeft()) ? this.getLeft().strahler() : 0;
        int sr = (this.hasRight()) ? this.getRight().strahler() : 0;
        if (sr == sl)
            return sl + 1;
        else
            return Math.min(sl, sr);
    }

    public Arbre<T> miroir() {
        Arbre<T> a = new Arbre<T>(this.getLabel());
        if (this.hasLeft())
            a.setRight(this.getLeft().miroir());
        if (this.hasRight())
            a.setLeft(this.right.miroir());
        return a;
    }

    public boolean isMirror(Arbre<T> other) {
        if (other == null)
            return false;
        if (!this.getLabel().equals(other.getLabel()))
            return false;
        boolean mirRight = true, mirLeft = true;
        if (this.hasLeft())
            mirRight = this.getRight().isMirror(other.getLeft());
        else if (other.hasLeft())
            return false;
        if (this.hasLeft())
            mirLeft = this.getLeft().isMirror(other.getRight());
        else if (other.hasRight())
            return false;

        return mirRight && mirLeft;
    }

    public int shortestPath() {
        if (this.isLeaf()) {
            return 1;
        }
        if (!this.hasLeft()) {
            return this.getRight().shortestPath() + 1;
        }
        if (!this.hasRight()) {
            return this.getLeft().shortestPath() + 1;
        }
        return Math.min(this.getRight().shortestPath() + 1, this.getLeft().shortestPath() + 1);
    }

    private String aff(int level) {
        if (this.isLeaf())
            return shift(level) + this.getLabel();
        else {
            String left = (hasLeft()) ? getLeft().aff(level + 1) : shift(level + 1) + "#";
            String right = (hasRight()) ? getRight().aff(level + 1) : shift(level + 1) + "#";
            return shift(level) + getLabel() + "\n" + left + "\n" + right;
        }
    }

    private String shift(int shift) {
        if (shift == 0) {
            return "";
        }
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < shift - 1; i++) {
            buffer.append(" ");
        }
        // buffer.append("");
        return buffer.toString();
    }

    public String toString() {
        return aff(0);
    }

    private void save(String a) {
        try {
            if (!new File("graph.txt").exists())
                new File("graph.txt").createNewFile();
            File fichier = new File("graph.txt");
            try {
                FileWriter writer = new FileWriter(fichier);
                try {
                    writer.write(a);
                } finally {
                    writer.close();
                }
            } catch (Exception e) {
                System.err.println("impossible write");
            }
        } catch (IOException e) {
            System.err.println("error flied");
        }
    }

    private void load() {
        String[] tree = new String[(int) (Math.pow(2, this.height()) - 1)];
        int h = 1;
        int i = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader("graph.txt"));
            String line = "";
            while ((line = in.readLine()) != null) {
                tree[i] = line.toString();
                i++;
            }
            in.close();
        } catch (Exception e) {
            System.err.println("error load");
        }
        String[][] treeTab = new String[this.height()][(int) (Math.pow(2, this.height()) - 1)];
        for (int j = 0; j < treeTab.length; j++) {
            for (int j2 = 0; j2 < treeTab[j].length; j2++) {
                treeTab[j][j2] = " ";
            }
        }
        int hi = 0;
        int hj = (int) (Math.pow(2, this.height()) - 1) / 2;
        treeTab[hi][hj] = tree[0];
        for (int j = 1; j < tree.length; j++) {
            if (tree[j] != null) {
                h = tree[j].length();
                if (h < hi) {
                    hi = h;
                    for (int j2 = 0; j2 < treeTab[hi].length; j2++) {
                        if (treeTab[hi][j2] != " ")
                            hj = j2;
                    }
                    hj += (int) (Math.pow(2, this.height() - (hi)));
                    treeTab[hi][hj] = tree[j].replaceAll(" ", "");
                } else {
                    if (hi == h) {
                        hj += (int) (Math.pow(2, this.height() - (hi)));
                        treeTab[hi][hj] = tree[j].replaceAll(" ", "");
                    } else {
                        hi += 1;
                        hj -= (int) (Math.pow(2, this.height() - (hi + 1)));
                        if (h == hi) {
                            treeTab[hi][hj] = tree[j].replaceAll(" ", "");
                        }
                    }
                }
            }
        }
        for (int j = 0; j < treeTab.length; j++) {
            for (int j2 = 0; j2 < treeTab[j].length; j2++) {
                System.out.print(treeTab[j][j2] + " ");
            }
            System.out.println();
        }
        // new ArbreGraph(treeTab);
    }

    public void Graph() {
        String a = this.toString();
        this.save(a);
        this.load();

    }

    public Node<T> prefixComplete() {
        if (this.isLeaf())
            return new Node<T>(this.getLabel());
        else {
            // Computing nodes for left and right sub trees
            Node<T> left = (this.hasLeft()) ? this.getLeft().prefixComplete() : null;
            Node<T> right = (this.hasRight()) ? this.getRight().prefixComplete() : null;
            // merging left and right nodes
            Node<T> p = left;
            if (left != null) {
                while (p.hasNext())
                    p = p.getNext();
                p.setNext(right);
                return new Node<T>(this.getLabel(), left);
            } else {
                return new Node<T>(this.getLabel(), right);
            }
        }
    }

    public Node<T> prefix() {
        if (this.isLeaf())
            return new Node<T>(this.getLabel());
        else {
            // Computing nodes for left and right sub trees
            Node<T> left = (this.hasLeft()) ? this.getLeft().prefix() : null;
            Node<T> right = (this.hasRight()) ? this.getRight().prefix() : null;
            // merging left and right nodes
            left.append(right);
            return new Node<T>(this.getLabel(), left);
        }
    }

    public Node<T> infixComplete() {
        if (this.isLeaf())
            return new Node<T>(this.getLabel());
        else {
            Node<T> left = (this.hasLeft()) ? this.getLeft().infixComplete() : null;
            Node<T> right = (this.hasRight()) ? this.getRight().infixComplete() : null;
            if (left == null)
                return new Node<T>(this.getLabel(), right);
            else {
                Node<T> p = left;
                while (p.hasNext())
                    p = p.getNext();
                Node<T> next = new Node<T>(this.getLabel(), right);
                p.setNext(next);
                return left;
            }
        }
    }

    public Node<T> infix() {
        if (this.isLeaf())
            return new Node<T>(this.getLabel());
        else {
            Node<T> left = (this.hasLeft()) ? this.getLeft().infix() : null;
            Node<T> right = (this.hasRight()) ? this.getRight().infix() : null;
            if (left == null)
                return new Node<T>(this.getLabel(), right);
            else {
                left.append(new Node<T>(this.getLabel(), right));
                return left;
            }
        }
    }

    public Node<T> postifixComplete() {
        if (this.isLeaf())
            return new Node<T>(this.getLabel());
        else {
            Node<T> left = (this.hasLeft()) ? this.getLeft().postifixComplete() : null;
            Node<T> right = (this.hasRight()) ? this.getRight().postifixComplete() : null;
            if (left == null && right == null)
                return new Node<T>(this.getLabel());
            else if (left == null && right != null) {
                Node<T> p = right;
                while (p.hasNext())
                    p = p.getNext();
                p.setNext(new Node<T>(this.getLabel()));
                return right;
            } else if (left != null && right == null) {
                Node<T> p = left;
                while (p.hasNext())
                    p = p.getNext();
                p.setNext(new Node<T>(this.getLabel()));
                return left;
            } else {
                Node<T> p = left;
                while (p.hasNext())
                    p = p.getNext();
                p.setNext(right);
                while (p.hasNext())
                    p = p.getNext();
                p.setNext(new Node<T>(this.getLabel()));
                return left;
            }
        }
    }

    public Node<T> postfix() {
        if (this.isLeaf())
            return new Node<T>(this.getLabel());
        else {
            Node<T> left = (this.hasLeft()) ? this.getLeft().postfix() : null;
            Node<T> right = (this.hasRight()) ? this.getRight().postfix() : null;
            if (left == null && right == null) {
                return new Node<T>(this.getLabel());
            } else if (left == null && right != null) {
                right.append(new Node<T>(this.getLabel()));
                return right;
            } else if (left != null && right == null) {
                left.append(new Node<T>(this.getLabel()));
                return left;
            } else {
                right.append(new Node<T>(this.getLabel()));
                left.append(right);
                return left;
            }
        }
    }

    public boolean hasLeft() {
        return this.getLeft() != null;
    }

    public boolean hasRight() {
        return this.getRight() != null;
    }

    public T getLabel() {
        return label;
    }

    public void setLabel(T label) {
        this.label = label;
    }

    public Arbre<T> getLeft() {
        return left;
    }

    public void setLeft(Arbre<T> left) {
        this.left = left;
    }

    public Arbre<T> getRight() {
        return right;
    }

    public void setRight(Arbre<T> right) {
        this.right = right;
    }

}