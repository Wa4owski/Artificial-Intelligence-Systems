package com.example.informed.search;

import com.example.City;

import java.util.*;

public class TotalScoreMinimising extends AbstractInformedSearch{

    private Map<String, Integer> distToFinishCity;

    public TotalScoreMinimising(Map<String, City> waysMap, Map<String, City> distMap) {
        super(waysMap, distMap);
        distToFinishCity = new HashMap<>();
    }

    @Override
    public void find(String startCityName, String finishCityName) {
        System.out.println("\nМетод минимизации суммарной оценки: ");
        distMap.get(finishCityName).getDestinations()
                .forEach(dest -> distToFinishCity.put(dest.getCity().getName(), dest.getDist()));
        distToFinishCity.put(finishCityName, 0);
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
            City.Destination nextDestFunc = new City.Destination(distMap.get(curCityName).getDestinations()
                    .stream()
                    .filter(dest -> dest.equals(way))
                    .findFirst().get());
            nextDestFunc.setDist(nextDestFunc.getDist()+distToFinishCity.get(nextDestFunc.getCity().getName()));
            distList.add(nextDestFunc);
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
