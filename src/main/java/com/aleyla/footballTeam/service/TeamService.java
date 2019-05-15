package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.repository.TeamRepository;
import org.springframework.stereotype.Component;

@Component
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
