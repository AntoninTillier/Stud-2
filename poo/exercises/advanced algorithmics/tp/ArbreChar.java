public class ArbreChar extends Arbre<Character> {

    public ArbreChar(Character _label) {
        super(_label);
    }

    public ArbreChar(Character _label, ArbreChar _left, ArbreChar _right) {
        super(_label, _left, _right);
    }

    private boolean isVowel(char l) {
        switch (l) {
            case 'a':
                return true;
            case 'e':
                return true;
            case 'i':
                return true;
            case 'o':
                return true;
            case 'u':
                return true;
            case 'y':
                return true;
            default:
                return false;
        }
    }

    public int nbLeafVowel() {
        if (this.isLeaf())
            return this.isVowel(this.getLabel()) ? 1 : 0;
        else {
            int left = (this.hasLeft() && this.isVowel(this.getLeft().getLabel())) ? ((ArbreChar) this.getLeft()).nbLeafVowel() : 0;
            int right = (this.hasRight() && this.isVowel(this.getRight().getLabel())) ? ((ArbreChar) this.getRight()).nbLeafVowel() : 0;
            return left + right;
        }
    }
}