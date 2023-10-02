public class TD5G implements Filter<Integer> {

    private int val;

    public TD5G(int val) {
        this.val = val;
    }

    @Override
    public boolean filter(Integer input) {
        return input > val;
    }

}