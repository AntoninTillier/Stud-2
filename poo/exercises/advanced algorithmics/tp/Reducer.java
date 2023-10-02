public interface Reducer<I, O> {
    // combine a list element I with the result of
    // reduction O on the next elements in the list
    // when list is empty or to combine with last element of list
    // use the value returned by getBase
    public O reduce(I input, O next);

    public O getBase();
}
