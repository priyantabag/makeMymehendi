package com.sup.mehandi.model;

/**
 * Created by Umakant Angadi on 12/24/2016.
 */
public class User {

    private String name;
    private String address;
    private Integer ratings;


    public User(String name, String address, Integer ratings) {
        this.name = name;
        this.address = address;
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }
}
