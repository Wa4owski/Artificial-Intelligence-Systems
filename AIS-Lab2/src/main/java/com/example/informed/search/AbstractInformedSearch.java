package com.example.informed.search;

import com.example.City;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractInformedSearch {

    Map<String, Boolean> used;
    Map<String, City> waysMap;
    Map<String, City> distMap;

    public AbstractInformedSearch(Map<String, City> waysMap, Map<String, City> distMap) {
        this.waysMap = waysMap;
        this.distMap = distMap;
        used = new HashMap<>();
        waysMap.keySet().forEach(name -> used.put(name, false));
    }

    abstract public void find(String startCityName, String finishCityName);
}

