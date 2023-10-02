public class Mutliple implements Filter<Integer> {

    private int n;

    public Mutliple(int n) {
        this.n = n;
    }

    @Override
    public boolean filter(Integer input) {
        return !((input % n == 0) && (input != n));
    }
}