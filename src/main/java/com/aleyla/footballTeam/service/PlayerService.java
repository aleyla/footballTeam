package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.exception.EntityNotFoundException;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import com.aleyla.footballTeam.repository.ContractRepository;
import com.aleyla.footballTeam.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PlayerService {
    private PlayerRepository playerRepository;
    private ContractRepository contractRepository;

    public PlayerService(PlayerRepository playerRepository, ContractRepository contractRepository) {
        this.playerRepository = playerRepository;
        this.contractRepository = contractRepository;
    }

    public Player findByid(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            throw new EntityNotFoundException();
        }
        return player;
    }

    public Player save(Player player) {
        validatePlayer(player);
        return playerRepository.save(player);
    }

    public void delete(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            throw new EntityNotFoundException();
        }
        playerRepository.deleteById(id);
    }

    public void update(Long id, Player player) {
        if (player.getId() != null && !id.equals(player.getId())) {
            throw new InvalidRequestException("id", id, "PLAYER_ID_MATCH");
        }
        if (player.getId() == null) {
            player.setId(id);
        }
        validatePlayer(player);
        playerRepository.save(player);
    }


    private void validatePlayer(Player player) {
        if (player == null) {
            throw new InvalidRequestException("player", player, "PLAYER_COULD_NOT_BE_EMPTY");
        }
        if (player.getName() == null || player.getName().isEmpty()) {
            throw new InvalidRequestException("name", player.getName(), "TEAM_NAME_COULD_NOT_BE_EMPTY");
        }
        if (player.getSurname() == null || player.getSurname().isEmpty()) {
            throw new InvalidRequestException("surname", player.getSurname(), "PLAYER_SURNAME_COULD_NOT_BE_EMPTY");
        }
        if (player.getBirthday() == null) {
            throw new InvalidRequestException("birthday", player.getBirthday(), "PLAYER_SURNAME_COULD_NOT_BE_EMPTY");
        }
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }


    public List<Player> findPlayerByTeamAndYear(Long teamId, Integer year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return playerRepository.findAllById(contractRepository.findAllPlayerIdsByTeamIdAndContractDate(teamId, startDate, endDate));
    }
}
