package models.FrameProcessing;

public class Buffer<T>{

    private int firstIndex;
    private int lastIndex;
    private T[] values;
    private final int capacity;
    private boolean capacityHasBeenReached;

    public Buffer(int capacity){
        if(capacity == 0){
            throw new RuntimeException("Cannot instantiate Buffer with capacity of 0");
        }
        values = (T[])new Object[capacity];
        this.capacity = capacity;
        firstIndex = 0;
        lastIndex = -1;
        capacityHasBeenReached = false;
    }

    private void incrementIndexes(){
        lastIndex++;
        if(lastIndex == capacity){
            capacityHasBeenReached = true;
            lastIndex = 0;
        }
        if(capacityHasBeenReached){
            if(lastIndex == firstIndex){
                firstIndex++;
                if(firstIndex == capacity){
                    firstIndex = 0;
                }
            }
        }
    }

    public void add(T value){
        incrementIndexes();
        values[lastIndex] = value;
    }

    public T get(int offset){
        int index = firstIndex + offset;
        return index >= capacity ? values[index - capacity] : values[index];
    }

    public T getFirst(){
        return values[firstIndex];
    }

    public T getLast(){
        return values[lastIndex];
    }

    public void onEachDo(BufferAction<T> action){
        for (int i = 0; i < capacity; i++) {
            if(this.get(i) == null)
                return;
            action.performOn(this.get(i));
        }
    }

    public interface BufferAction<T> {
        void performOn(T value);
    }

}
