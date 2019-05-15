package com.aleyla.footballTeam.controller;

import com.aleyla.footballTeam.service.TeamService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping(path = "team/v1")
public class TeamController {
    
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
}
