package org.ashish.springboot.practice.model;

import javax.persistence.*;
import java.util.Objects;

//@Table(name = "team",uniqueConstraints = @UniqueConstraint(columnNames = {"teamId"}))
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long teamId;

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

    public Long getTeamId() {
        return teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId.equals(team.teamId) &&
                fullName.equals(team.fullName) &&
                city.equals(team.city) &&
                Objects.equals(nickName, team.nickName) &&
                shortName.equals(team.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, fullName, city, nickName, shortName);
    }
}
