public class TD5Produit implements Reducer<Couple, Double> {

    @Override
    public Double reduce(Couple input, Double next) {

        return (double) input.getSecond() * next;
    }

    @Override
    public Double getBase() {

        return 1d;
    }

}