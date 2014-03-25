package edu.sjsu.fly5.util;
import java.util.HashMap;
import java.util.Map;

public class Fly5Cache<K,V> /*implements Cache<K, V>*/ {

    private Map<K,V> map;
    
    public Fly5Cache(){
        map = new HashMap<>();
    }
    
    public void put(K key, V value) {
        map.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

    public void clear() {
        map.clear();
    }

    public V remove(K key) {
        return map.remove(key);
    }

    public boolean containsKey(K key){
        return map.containsKey(key);
    }
}
