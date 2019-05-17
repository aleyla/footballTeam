package com.aleyla.footballTeam.controller;

import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(path = "player/v1")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Player player) {
        Player saved = playerService.save(player);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().pathSegment("player", "v1", String.valueOf(saved.getId())).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "all")
    public @ResponseBody
    List<Player> findAll() {
        return playerService.findAll();
    }

    @GetMapping(path = "{id}")
    public @ResponseBody
    Player findByid(@PathVariable("id") Long id) {
        return playerService.findByid(id);
    }

    @DeleteMapping(path = "{id}")
    public @ResponseBody
    void delete(@PathVariable("id") Long id) {
        playerService.delete(id);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    void update(@PathVariable("id") Long id, @RequestBody Player player) {
        playerService.update(id, player);
    }

    @GetMapping(path = "findPlayerByTeamAndYear")
    public @ResponseBody List<Player> findPlayerByTeamAndYear(@RequestParam("teamId") Long teamId, @RequestParam("year") Integer year) {
        return playerService.findPlayerByTeamAndYear(teamId, year);
    }
}
