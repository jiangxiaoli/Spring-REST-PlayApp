package edu.sjsu.cmpe275.lab3.model;

import javax.persistence.*;
import java.io.Serializable;

//set embeddable for nested embedded in player or sponsor classes
@Embeddable
public class Address implements Serializable{
    private String street;
    private String city;
    private String state;
    private String zip;

    public Address(){

    }

    public Address(String street, String city, String state, String zip){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet(){
        return street;
    }

    public String getCity(){
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(String zip) { this.zip = zip;}
}
