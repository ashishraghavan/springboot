package org.ashish.springboot.practice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String name;

    @Column
    private String country;

    Player(){}

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
