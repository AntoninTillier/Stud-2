public class SommeCarre implements Reducer<Double, Double> {

    @Override
    public Double reduce(Double input, Double next) {
        double c = input * input;
        return c + next;
        // next += input*input;
    }

    @Override
    public Double getBase() {
        return 0.0;
    }


}