package org.ashish.springboot.practice.model;

import javax.persistence.*;
import java.util.Objects;

//@Table(name = "player",uniqueConstraints = @UniqueConstraint(columnNames = {"playerId","name"}))
//uniqueConstraints = {@UniqueConstraint(columnNames = "name",name = "uniqueNameConstraint")}
//,indexes = { @Index(name = "uniquename", columnList = "name") }
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false,length = 100,unique = true)
    private String name;

    @Column
    private String country;

    public Player(){}

    public Player(String firstName,String lastName,String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = this.firstName + " "+this.lastName;
        this.country=country;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.firstName + " "+this.lastName;
    }

    public void setName(String name) {
        this.name = this.firstName + " " + this.lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId.equals(player.playerId) &&
                name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, name);
    }
}
