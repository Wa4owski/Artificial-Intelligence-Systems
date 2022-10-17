package com.example.not.informed.search;

import com.example.City;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BidirectionalSearch extends AbstractNotInformedSearch {
    Map<String, String> usedFromWhere;

    public BidirectionalSearch(Map<String, City> map) {
        super(map);
        this.usedFromWhere = new HashMap<>();
        map.keySet().forEach(name -> usedFromWhere.put(name, null));
    }

    @Override
    public void find(String startCityName, String finishCityName) {
        System.out.println("Двунаправленный поиск: ");
        solute(startCityName, finishCityName);
        System.out.println("------------------");
    }

    private void solute(String startCityName, String finishCityName){
        usedFromWhere.put(startCityName, startCityName);
        usedFromWhere.put(finishCityName, finishCityName);
        City startCity = map.get(startCityName);
        City finishCity = map.get(finishCityName);
        Queue<City> fromStart = new LinkedList<>();
        Queue<City> fromFinish = new LinkedList<>();
        fromStart.add(startCity);
        fromFinish.add(finishCity);

        while(!fromStart.isEmpty() && !fromFinish.isEmpty()){
            if(bfsByQueue(fromStart, startCityName, finishCityName))
                return;
            if(bfsByQueue(fromFinish, finishCityName, startCityName))
                return;
        }
        while(!fromStart.isEmpty()){
            if(bfsByQueue(fromStart, startCityName, finishCityName))
                return;
        }
        while(!fromFinish.isEmpty()){
            if(bfsByQueue(fromFinish, finishCityName, startCityName))
                return;
        }
    }

    private boolean bfsByQueue(Queue<City> queue, String fromCityName, String aimCityName){
        City curCity = queue.poll();
        used.put(curCity.getName(), true);
        usedFromWhere.put(curCity.getName(), fromCityName);
        System.out.println("Выехали из " + fromCityName + ":");
        for(City.Destination destination : curCity.getDestinations()){
            City newCity = destination.getCity();
            if(!used.get(newCity.getName())){
                queue.add(newCity);
                System.out.println(curCity.getName() + " -> " + newCity.getName());
            }
            else{
                if(usedFromWhere.get(newCity.getName()).equals(aimCityName)){
                    System.out.println(curCity.getName() + " -> " + aimCityName);
                    return true;
                }
            }
        }
        return false;
    }
}
