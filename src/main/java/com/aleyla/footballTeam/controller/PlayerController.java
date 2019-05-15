package com.aleyla.footballTeam.controller;

import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @GetMapping(path = "{id}")
    public @ResponseBody
    Player getById(@PathVariable("id") Long id) {
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
        if (player.getId() != null && !id.equals(player.getId())) {
            //TODO Exception
        }
        if (player.getId() == null) {
            player.setId(id);
        }
        playerService.save(player);
    }

}
