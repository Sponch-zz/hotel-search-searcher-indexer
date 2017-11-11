package com.hotels.search.result.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesponc on 6/13/16.
 */
public class SearchResult {

    private long numFound;

    private List<Hotel> hotels = new ArrayList<>();

    private List<City> cities = new ArrayList<>();

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCities(City city) {
        this.cities.add(city);
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }
}



