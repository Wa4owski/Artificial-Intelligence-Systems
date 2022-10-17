package com.example.not.informed.search;

import com.example.City;

import java.util.Map;

public class DFS extends AbstractNotInformedSearch {
    public DFS(Map<String, City> map){
        super(map);
    }

    @Override
    public void find(String startCityName, String finishCityName) {
        System.out.println("\nОбход в глубину: ");
        dfs(startCityName, finishCityName);
        System.out.println("------------------");
    }

    private boolean dfs(String currentCityName, String finishCityName){
        City currentCity = map.get(currentCityName);
        used.put(currentCityName, true);
        for(City.Destination destination : currentCity.getDestinations()){
            City destCity = destination.getCity();
            if(!used.get(destCity.getName())){
                System.out.println(currentCityName + " -> " + destCity.getName());
                if(destCity.getName().equals(finishCityName)){
                    return true;
                }
                if(dfs(destCity.getName(), finishCityName))
                    return true;
                System.out.println(destCity.getName() + " -> " + currentCityName);
            }
        }
        return false;
    }
}
