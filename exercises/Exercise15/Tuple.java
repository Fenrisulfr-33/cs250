package cs250.hw1;

interface Tuple<T> {
    public boolean contains(T value);
    public T get(int index);
    public int indexOf(T value);
    public int size();
    public T[] toArray();
    public TupleStructure<T> join(TupleStructure<T> tuple);
    public TupleStructure<T> multiply(int times);
}
