package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.repository.PlayerRepository;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
}
