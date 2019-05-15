package com.aleyla.footballTeam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "player")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_player")
    @SequenceGenerator(name = "seq_player", sequenceName = "seq_player", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "BIRTHDAY", columnDefinition = "DATE")
    private LocalDate birthday;

    @Column(name = "EXPERIENCE_DURATION")
    private BigDecimal experienceDuration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getExperienceDuration() {
        return experienceDuration;
    }

    public void setExperienceDuration(BigDecimal experienceDuration) {
        this.experienceDuration = experienceDuration;
    }
}
