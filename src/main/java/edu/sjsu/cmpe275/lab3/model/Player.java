package edu.sjsu.cmpe275.lab3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPlayer")
    private long id;

    //not null
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;

    //not null, has to be unique
    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Embedded
    private Address address;

    //many to one relationship between sponsor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sponsor_idSponsor")
    private Sponsor sponsor;


    //many to many relationship between other players, self
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Opponent",
            joinColumns = {@JoinColumn(name = "Player_idPlayer1", referencedColumnName = "idPlayer" )},
            inverseJoinColumns = {@JoinColumn(name = "Player_idPlayer2", referencedColumnName = "idPlayer")}
    )
    private List<Player> opponents;

    // constructor
    public Player(String firstname, String lastname, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Player(){

    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public List<Player> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }
}
