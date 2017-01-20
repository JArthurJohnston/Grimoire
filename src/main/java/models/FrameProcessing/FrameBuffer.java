package models.FrameProcessing;

import java.util.Enumeration;

public class FrameBuffer implements Enumeration<ClusterCollection>{

    private int firstIndex;
    private int lastIndex;
    private int iteratorIndex;
    private ClusterCollection[] frameClusters;
    private final int capacity;
    private boolean capacityHasBeenReached;

    public FrameBuffer(int capacity){
        frameClusters = new ClusterCollection[capacity];
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

    public void push(ClusterCollection clusters){
        incrementIndexes();
        frameClusters[lastIndex] = clusters;
    }

    public ClusterCollection getFirst(){
        return frameClusters[firstIndex];
    }

    public ClusterCollection getLast(){
        return frameClusters[lastIndex];
    }

    public boolean hasMoreElements() {
        return iteratorIndex <= lastIndex;
    }

    public ClusterCollection nextElement() {
        return frameClusters[iteratorIndex++];
    }
}
