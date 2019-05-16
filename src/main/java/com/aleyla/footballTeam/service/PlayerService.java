package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import com.aleyla.footballTeam.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Player update(Long id, Player player) {
        if (player.getId() != null && !id.equals(player.getId())) {
            throw new InvalidRequestException("id", id, "PLAYER_ID_NOT_MATCH");
        }
        if (player.getId() == null) {
            player.setId(id);
        }
        //TODO Controller

        return playerRepository.save(player);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }
}
