package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.repository.PlayerRepository;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player findByid(Long id){
        return playerRepository.findById(id).orElse(null);
    }
    public Player save(Player player){
        //TODO Controller
        return playerRepository.save(player);
    }

    public void delete(Long id){
        playerRepository.deleteById(id);
    }
}
