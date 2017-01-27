package models.FrameProcessing;

import java.util.Iterator;

public class Buffer<T> {

    private int firstIndex;
    private int lastIndex;
    private T[] values;
    private final int capacity;
    private boolean capacityHasBeenReached;
    private boolean isEmpty;

    public Buffer(int capacity){
        isEmpty = true;
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
        isEmpty = false;
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
        for (int i = capacity - 1; i >= 0; i--) {
            if(this.get(i) == null)
                return;
            action.performOn(this.get(i));
        }

    }

    public boolean isEmpty(){
        return isEmpty;
    }

    public BufferIterator iterator(){
        return new BufferIterator();
    }

    public interface BufferAction<T> {
        void performOn(T value);
    }

    public class BufferIterator implements Iterator<T> {
        private int index;
        BufferIterator(){
            index = capacity - 1;
        }

        @Override
        public boolean hasNext() {
            return index > 0 && values[index] != null;
        }

        @Override
        public T next() {
            return Buffer.this.get(index--);
        }
    }

}
