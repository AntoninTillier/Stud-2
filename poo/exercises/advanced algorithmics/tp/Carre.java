public class Carre implements Mapper<Double, Double> {
    @Override
    public Double mapTo(Double input) {
        return input * input;
    }

}