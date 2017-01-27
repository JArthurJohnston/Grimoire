package models.PixelProcessing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arthur on 26/01/17.
 */
public class Aggregator<T>{
    final Map<T, Integer> valueMap;
    T aggregate;

    public Aggregator(T defaultValue, T... values){
        valueMap = new HashMap<>();
        aggregate = defaultValue;
        for (T eachValue : values) {
            valueMap.put(eachValue, 0);
        }
    }

    public void add(T value){
        if(valueMap.isEmpty()){
            aggregate = value;
        } else {
            valueMap.put(value, valueMap.get(value) + 1);
            if(valueMap.get(value) > valueMap.get(aggregate)){
                aggregate = value;
            }
        }
    }

    public void reset(){
        for (T eachValue : this.valueMap.keySet()) {
            valueMap.put(eachValue, 0);
        }
    }

    public T aggregate(){
        return aggregate;
    }
}