package cs250.hw1;

public class TupleStructure<T> implements Tuple<T> {
    private T[] tuple;
    private int size;

    public TupleStructure(T[] tuple){
        this.tuple = tuple;
        this.size = tuple.length;
    }

    @Override
    public boolean contains(T value){
        for (T element : tuple){
            if (element.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index){
        T foundElement = null;
        int listIndex = 0;
        for (T element : tuple){
            if (listIndex == index){
                foundElement = element;
            }
            listIndex++;
        }
        return foundElement;
    }

    @Override
    public int indexOf(T value){
        int index = 0;
        for (T element : tuple){
            if (element.equals(value)){
                break;
            }
            index++;
        }
        return index;

    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public T[] toArray(){
        return tuple;
    }

    @Override
    public TupleStructure<T> join(TupleStructure<T> tuple){
        int newTupleLength = this.size() + tuple.size();
        T[] newTupleArray = (T[]) new Object[newTupleLength];
        // TupleStructure<T> newTuple = new TupleStructure<T>();
        for (int i = 0; i < this.size(); i++){
            newTupleArray[i] = this.tuple[i];
        }
        for (int i = this.size(); i < newTupleLength; i++){
            newTupleArray[i] = tuple.get(i - this.size());
        }
        TupleStructure<T> newTupleStructure = new TupleStructure<>(newTupleArray);
        return newTupleStructure;
    }

    @Override
    public TupleStructure<T> multiply(int times){
        TupleStructure<T> newTuple = new TupleStructure<>(tuple);
        for (int i = 1; i < times; i++){
            TupleStructure<T> addStructure = new TupleStructure<>(tuple);
            newTuple.join(addStructure);
        }
        return newTuple;
    }
}
