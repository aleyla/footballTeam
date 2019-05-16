package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import com.aleyla.footballTeam.repository.TeamRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findByid(Long id){
        return teamRepository.findById(id).orElse(null);
    }

    public Team save(Team team){
        //TODO Controller
        return teamRepository.save(team);
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    public Team update(Long id, Team team) {
        if (team.getId() != null && !id.equals(team.getId())) {
            throw new InvalidRequestException("id", id, "TEAM_ID_NOT_MATCH");
        }
        if (team.getId() == null) {
            team.setId(id);
        }
        //TODO Controller
        return teamRepository.save(team);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
