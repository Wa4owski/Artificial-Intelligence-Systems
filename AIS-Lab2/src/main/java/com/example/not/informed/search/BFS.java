package com.example.not.informed.search;

import com.example.City;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFS extends AbstractNotInformedSearch {

    public BFS(Map<String, City> map){
        super(map);
    }

    @Override
    public void find(String startCityName, String finishCityName) {
        System.out.println("Обход в ширину: ");
        bfs(startCityName, finishCityName);
        System.out.println("------------------");
    }

    void bfs(String startCityName, String finishCityName){
        Queue<City> queue = new LinkedList<>();
        queue.add(map.get(startCityName));
        while (!queue.isEmpty()){
            City city = queue.poll();
            used.put(city.getName(), true);
            for(City.Destination destination : city.getDestinations()){
                City newCity = destination.getCity();
                if(!used.get(newCity.getName())){
                    System.out.println(city.getName() + " -> " + newCity.getName());
                    if(newCity.getName().equals(finishCityName)){
                        return;
                    }
                    queue.add(newCity);
                }
            }
        }
    }
}
