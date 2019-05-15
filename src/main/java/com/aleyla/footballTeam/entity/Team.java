package com.aleyla.footballTeam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "team")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_team")
    @SequenceGenerator(name = "seq_team", sequenceName = "seq_team", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
