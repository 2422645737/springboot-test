package com.shiyueoe.demo_07_29;

import java.util.LinkedHashMap;
import java.util.Map;

public class UMap {
    public static <K,V> Map<K,V> of(Object... keyAndValue){
        if(keyAndValue.length % 2 != 0){
            return null;
        }
        Map<K,V> map = new LinkedHashMap<>();
        for(int i = 0; i < keyAndValue.length; i+= 2){
            K key = (K)keyAndValue[i];
            V value = (V)keyAndValue[i + 1];
            map.put(key, value);
        }
        return map;
    }
}
