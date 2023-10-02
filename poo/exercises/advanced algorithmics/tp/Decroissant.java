public class Decroissant implements Reducer<Double, Couple> {

    @Override
    public Couple reduce(Double input, Couple next) {
        if (next == null)
            return new Couple(input, true);
        else
            return (input > (double) next.getFirst()) ? new Couple(input, next.getSecond()) : new Couple(input, false);
    }

    @Override
    public Couple getBase() {
        return null;
    }

}