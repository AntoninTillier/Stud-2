public class TD5Nom implements Mapper<Couple, String> {


    @Override
    public String mapTo(Couple input) {

        return (String) input.getFirst();
    }

}