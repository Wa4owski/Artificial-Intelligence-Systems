package com.example.informed.search;

import com.example.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FirstBestMatch extends AbstractInformedSearch{

    public FirstBestMatch(Map<String, City> waysMap, Map<String, City> distMap) {
        super(waysMap, distMap);
    }

    @Override
    public void find(String startCityName, String finishCityName) {
        System.out.println("\nЖадный поиск по первому наилучшему соответствию: ");
        solute(startCityName, finishCityName, 0);
        System.out.println("------------------");
    }

    private boolean solute(String curCityName, String finishCityName, int curPathLen){
        if(curCityName.equals(finishCityName)){
            return true;
        }
        used.put(curCityName, true);
        City curCity = waysMap.get(curCityName);
        List<City.Destination> distList = new ArrayList<>();
        for(City.Destination way: curCity.getDestinations()){
                distList.add(distMap.get(curCityName).getDestinations()
                        .stream()
                        .filter(dest -> dest.equals(way))
                        .findFirst().get());
        }
        Collections.sort(distList);
        for(City.Destination dist : distList){
            if(!used.get(dist.getCity().getName())){
                City.Destination nextDestination = curCity.getDestinations()
                        .stream()
                        .filter(dest -> dest.equals(dist))
                        .findFirst().get();
                City nextCity = nextDestination.getCity();
                int wayLen = nextDestination.getDist();
                System.out.printf(curCityName + " -> " + nextCity.getName() + " Путь: %d\n", curPathLen+wayLen);
                if(solute(nextCity.getName(), finishCityName, curPathLen+wayLen))
                    return true;
            }
        }
        return false;
    }
}
