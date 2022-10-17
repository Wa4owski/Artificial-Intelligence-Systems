package com.example.not.informed.search;

import com.example.City;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractNotInformedSearch {

    Map<String, Boolean> used;
    Map<String, City> map;

    public AbstractNotInformedSearch(Map<String, City> map) {
        this.map = map;
        used = new HashMap<>();
        map.keySet().forEach(name -> used.put(name, false));
    }

    abstract public void find(String startCityName, String finishCityName);
}

