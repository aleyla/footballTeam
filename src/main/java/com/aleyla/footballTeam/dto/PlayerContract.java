package com.aleyla.footballTeam.dto;

import com.aleyla.footballTeam.entity.Contract;
import com.aleyla.footballTeam.entity.Player;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerContract {
    private Player player;
    private List<Contract> contracts;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
