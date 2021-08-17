package com.example.oop_avancerad_3.Entity;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carId;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User owner;

    private String regPlate;
    //description of car, if needed
    private String carDesc;
    //sedan, combi, SUV etc. the types could be a separate entity, and this just a reference to that.
    private String type;
    //number of passengers, useful for users searching
    private int numberPassengers;

    private String latitude;

    private String longitude;

    public Car(){

    }
    public Car(User owner, String regPlate, String carDesc, String type, int numberPassengers, String latitude, String longitude){
        this.owner = owner;
        this.regPlate = regPlate;
        this.carDesc = carDesc;
        this.type = type;
        this.numberPassengers = numberPassengers;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getRegPlate() {
        return regPlate;
    }

    public void setRegPlate(String regPlate) {
        regPlate = regPlate;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        carDesc = carDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberPassengers() {
        return numberPassengers;
    }

    public void setNumberPassengers(int numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", owner=" + owner +
                ", regPlate='" + regPlate + '\'' +
                ", carDesc='" + carDesc + '\'' +
                ", type='" + type + '\'' +
                ", numberPassengers=" + numberPassengers +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}