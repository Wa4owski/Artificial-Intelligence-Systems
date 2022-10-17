package com.example.not.informed.search;

import com.example.City;

import java.util.Map;

public class DeepLimitDFS extends DeepDependedDFS{

    public DeepLimitDFS(Map<String, City> map) {
        super(map);
        super.maxCurrentStep = 5;
    }

    @Override
    public void find(String startCityName, String finishCityName) {
        System.out.println("\nПоиск с ограничением глубины: ");
        System.out.printf("\nМаксимальная глубина - %d: \n", maxCurrentStep);
        dfs(startCityName, finishCityName, 1);
        System.out.println("------------------");
    }

}
