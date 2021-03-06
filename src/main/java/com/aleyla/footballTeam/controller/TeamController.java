package com.aleyla.footballTeam.controller;

import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequestMapping(path = "team/v1")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Team team) {
        Team saved = teamService.save(team);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().pathSegment("contract", "v1", String.valueOf(saved.getId())).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public @ResponseBody
    List<Team> findAll() {
        return teamService.findAll();
    }

    @GetMapping(path = "{id}")
    public @ResponseBody
    Team findById(@PathVariable("id") Long id) {
        return teamService.findById(id);
    }

    @DeleteMapping(path = "{id}")
    public @ResponseBody
    void delete(@PathVariable("id") Long id) {
        teamService.delete(id);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    void update(@PathVariable("id") Long id, @RequestBody Team team) {
        teamService.update(id, team);
    }

    @GetMapping(path = "team-by-player")
    public @ResponseBody
    List<Team> findTeamByPlayerId(@RequestParam("playerId") Long id) {
        return teamService.findTeamByPlayerId(id);
    }
}
