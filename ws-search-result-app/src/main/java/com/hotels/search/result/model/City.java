package com.hotels.search.result.model;

/**
 * Created by cesponc on 6/13/16.
 */
public class City {

    private String name;

    private long count;

    public City(String name, long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
