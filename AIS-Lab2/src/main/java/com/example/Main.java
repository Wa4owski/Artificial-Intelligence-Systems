package com.example;

import com.example.informed.search.AbstractInformedSearch;
import com.example.not.informed.search.AbstractNotInformedSearch;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        final String startName = "Рига", finishName = "Уфа"; // 4

        Scanner scanner = new Scanner(new File("src/main/resources/ways.txt"));
        Map<String, City> waysMap = new HashMap<>();
        parseFile(scanner, waysMap);


        Reflections reflections = new Reflections("com.example");
        Set<Class<? extends AbstractNotInformedSearch>> subTypes = reflections.getSubTypesOf(AbstractNotInformedSearch.class);

        for(Class<? extends AbstractNotInformedSearch> clazz : subTypes){
            if(!Modifier.isAbstract(clazz.getModifiers())) {
                AbstractNotInformedSearch searchMethod = clazz.getConstructor(Map.class).newInstance(waysMap);
                searchMethod.find(startName, finishName);
            }
        }

        scanner = new Scanner(new File("src/main/resources/distances.txt"));
        Map<String, City> distMap = new HashMap<>();
        parseFile(scanner, distMap);
        System.out.println();

        Set<Class<? extends AbstractInformedSearch>> informedSubTypes = reflections.getSubTypesOf(AbstractInformedSearch.class);

        for(Class<? extends AbstractInformedSearch> clazz : informedSubTypes){
            if(!Modifier.isAbstract(clazz.getModifiers())) {
                AbstractInformedSearch searchMethod = clazz.getConstructor(Map.class, Map.class).newInstance(waysMap, distMap);
                searchMethod.find(startName, finishName);
            }
        }
    }

    static void parseFile(Scanner scanner, Map<String, City> map){
        while (scanner.hasNext()){
            String[] input = scanner.nextLine().split(" ");
            String name1 = input[0];
            String name2 = input[1];
            int dist = Integer.parseInt(input[2]);
            City city1 = map.get(name1);
            if(city1 == null){
                city1 = new City(name1);
                map.put(name1, city1);
            }
            City city2 = map.get(name2);
            if(city2 == null){
                city2 = new City(name2);
                map.put(name2, city2);
            }
            city1.addDestination(new City.Destination(city2, dist));
            city2.addDestination(new City.Destination(city1, dist));
        }
    }



}
