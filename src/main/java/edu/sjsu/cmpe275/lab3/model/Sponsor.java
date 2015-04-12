package edu.sjsu.cmpe275.lab3.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Sponsor")
public class Sponsor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSponsor")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private Address address;

    public Sponsor(String name){
        this.name = name;
    }

    public Sponsor(){
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Address getAddress(){
        return address;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setAddress(Address address){
        this.address = address;
    }

}
