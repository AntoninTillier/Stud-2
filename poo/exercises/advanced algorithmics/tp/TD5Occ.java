public class TD5Occ implements Filter<Integer> {

    private int val;

    public TD5Occ(int val) {
        this.val = val;
    }

    @Override
    public boolean filter(Integer input) {
        return !input.equals(val);
    }

}
