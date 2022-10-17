package com.example.not.informed.search;

import com.example.City;

import java.util.HashMap;
import java.util.Map;

public class IterativeDFS extends DeepDependedDFS {

    private final int maxDeep = 10;

    public IterativeDFS(Map<String, City> map){
        super(map);
        super.maxCurrentStep = 1;
    }


    @Override
    public void find(String startCityName, String finishCityName) {
        System.out.println("\nПоиск с итеративным углублением: ");
        while(maxCurrentStep <= maxDeep) {
            System.out.printf("\nМаксимальная глубина - %d: \n", maxCurrentStep);
            if (super.dfs(startCityName, finishCityName, 1)) {
                break;
            }
            used = new HashMap<>();
            map.keySet().forEach(name -> used.put(name, false));
            maxCurrentStep++;
        }
        System.out.println("------------------");
    }

}
