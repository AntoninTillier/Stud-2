
public interface Filter<I> {
    // return true if the entry input verifies a condition
    // expressed in the filter function
    public boolean filter(I input);
}
