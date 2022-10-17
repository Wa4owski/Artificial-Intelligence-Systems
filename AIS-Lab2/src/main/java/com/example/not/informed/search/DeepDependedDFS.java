package com.example.not.informed.search;

import com.example.City;

import java.util.Map;

public abstract class DeepDependedDFS extends AbstractNotInformedSearch {

    int maxCurrentStep;

    public DeepDependedDFS(Map<String, City> map){
        super(map);
    }

    boolean dfs(String currentCityName, String finishCityName, int step){
        City currentCity = map.get(currentCityName);
        used.put(currentCityName, true);
        for(City.Destination destination : currentCity.getDestinations()){
            City destCity = destination.getCity();
            if(!used.get(destCity.getName())){
                System.out.println(currentCityName + " -> " + destCity.getName());
                if(destCity.getName().equals(finishCityName)){
                    return true;
                }
                if(step < maxCurrentStep && dfs(destCity.getName(), finishCityName, step+1))
                    return true;
                System.out.println(destCity.getName() + " -> " + currentCityName);
            }
        }
        return false;
    }
}
