public class TD5NomMin implements Reducer<Couple, Couple> {

    @Override
    public Couple reduce(Couple input, Couple next) {
        if (next == null)
            return input;
        else
            return (double) (input.getSecond()) < (double) (next.getSecond()) ? input : next;
    }

    @Override
    public Couple getBase() {
        return null;
    }

}
