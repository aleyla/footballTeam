package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.repository.TeamRepository;
import org.springframework.stereotype.Component;

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
}
