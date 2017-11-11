package com.hotels.search.result.model;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by cesponc on 6/13/16.
 */
public class Hotel {

    @Field
    private long id;

    @Field
    private String name;

    @Field
    private String city;

    @Field
    private String country;

    @Field
    private int stars;

    @Field
    private long bookings;

    @Field
    private float score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public long getBookings() {
        return bookings;
    }

    public void setBookings(long bookings) {
        this.bookings = bookings;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
