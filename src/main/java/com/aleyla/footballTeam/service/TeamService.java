package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.exception.EntityNotFoundException;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import com.aleyla.footballTeam.repository.ContractRepository;
import com.aleyla.footballTeam.repository.TeamRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamService {
    private TeamRepository teamRepository;
    private ContractRepository contractRepository;

    public TeamService(TeamRepository teamRepository, ContractRepository contractRepository) {
        this.teamRepository = teamRepository;
        this.contractRepository = contractRepository;
    }

    public Team findByid(Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team == null) {
            throw new EntityNotFoundException();
        }
        return team;
    }

    public Team save(Team team) {
        validateTeam(team);
        return teamRepository.save(team);
    }

    public void delete(Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team == null) {
            throw new EntityNotFoundException();
        }
        teamRepository.deleteById(id);
    }

    public void update(Long id, Team team) {
        if (team.getId() != null && !id.equals(team.getId())) {
            throw new InvalidRequestException("id", id, "TEAM_ID_NOT_MATCH");
        }
        if (team.getId() == null) {
            team.setId(id);
        }
        validateTeam(team);
        teamRepository.save(team);
    }

    private void validateTeam(Team team) {
        if (team == null) {
            throw new InvalidRequestException("team", team, "TEAM_COULD_NOT_BE_EMPTY");
        }
        if (team.getName() == null || team.getName().isEmpty()) {
            throw new InvalidRequestException("name", team.getName(), "TEAM_NAME_COULD_NOT_BE_EMPTY");
        }
        if (team.getCurrencyCode() == null || team.getCurrencyCode().isEmpty()) {
            throw new InvalidRequestException("currencyCode", team, "TEAM_CURRENCY_CODE_COULD_NOT_BE_EMPTY");
        }

        //Update edebilsin ama aynı isimli kaydedemesin
        Team sameNameTeam = teamRepository.findByName(team.getName());
        if (sameNameTeam != null && !sameNameTeam.getName().equalsIgnoreCase(team.getName())) {
            throw new InvalidRequestException("name", team.getName(), "TEAM_NAME_MUST_BE_UNIQUE");
        }else if(sameNameTeam != null && sameNameTeam.getId() != team.getId() && sameNameTeam.getName().equalsIgnoreCase(team.getName())){
            throw new InvalidRequestException("name", team.getName(), "TEAM_NAME_MUST_BE_UNIQUE");
        }
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public List<Team> findTeamByPlayerId(Long playerId) {
        return teamRepository.findAllById(contractRepository.findAllTeamIdsByPlayerId(playerId));
    }
}
