public class TD5Multi implements Mapper<Couple, Couple> {

    private double val;

    public TD5Multi(double val) {
        this.val = val;
    }

    @Override
    public Couple mapTo(Couple input) {
        return new Couple(input.getFirst(), (double) (input.getSecond()) * val);
    }
}