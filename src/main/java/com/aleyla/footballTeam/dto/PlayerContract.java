package com.aleyla.footballTeam.dto;

import com.aleyla.footballTeam.entity.Player;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerContract {
    private Player player;
    private List<TransferSuggest> suggests;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<TransferSuggest> getSuggests() {
        return suggests;
    }

    public void setSuggests(List<TransferSuggest> suggests) {
        this.suggests = suggests;
    }
}
