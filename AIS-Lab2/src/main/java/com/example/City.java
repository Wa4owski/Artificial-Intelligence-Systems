package com.example;

import java.util.ArrayList;

public class City {
    private String name;

    private ArrayList<Destination> destinations;

    public City(String name) {
        this.name = name;
        destinations = new ArrayList<>();
    }

    public static class Destination implements Comparable<Destination>{
        private City city;
        private int dist;

        public Destination(City city, Integer dist) {
            this.city = city;
            this.dist = dist;
        }

        public Destination(Destination destination) {
            this.city = destination.getCity();
            this.dist = destination.getDist();
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public int getDist() {
            return dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Destination that = (Destination) o;
            return city.equals(that.city);
        }

        @Override
        public int compareTo(Destination dest) {
            if(dest.getDist() == dist){
                return 0;
            }
            return dest.getDist() > dist ? -1 : 1;
        }
    }



    public String getName() {
        return name;
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void addDestination(Destination destination){
        destinations.add(destination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

}
