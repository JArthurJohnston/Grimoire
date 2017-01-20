package models.FrameProcessing;

import java.util.Enumeration;

public class Buffer<T> implements Enumeration<T>{

    private int firstIndex;
    private int lastIndex;
    private int iteratorIndex;
    private T[] frameClusters;
    private final int capacity;
    private boolean capacityHasBeenReached;

    public Buffer(int capacity){
        frameClusters = (T[])new Object[capacity];
        this.capacity = capacity;
        firstIndex = 0;
        lastIndex = -1;
        iteratorIndex = 0;
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
        iteratorIndex = firstIndex;
    }

    public void push(T value){
        incrementIndexes();
        frameClusters[lastIndex] = value;
    }

    public T getFirst(){
        return frameClusters[firstIndex];
    }

    public T getLast(){
        return frameClusters[lastIndex];
    }

    public boolean hasMoreElements() {
        return iteratorIndex <= lastIndex;
    }

    public T nextElement() {
        return frameClusters[iteratorIndex++];
    }
}
