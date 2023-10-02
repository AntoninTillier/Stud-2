public class Somme implements Reducer<Double, Double> {

    @Override
    public Double reduce(Double input, Double next) {
        return input + next;
    }

    @Override
    public Double getBase() {
        return 0.0;
    }

}
