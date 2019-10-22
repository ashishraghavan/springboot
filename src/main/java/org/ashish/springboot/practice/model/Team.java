package org.ashish.springboot.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private String city;

    @Column
    private String nickName;

    @Column
    private String shortName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id) &&
                fullName.equals(team.fullName) &&
                city.equals(team.city) &&
                Objects.equals(nickName, team.nickName) &&
                shortName.equals(team.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, city, nickName, shortName);
    }
}
