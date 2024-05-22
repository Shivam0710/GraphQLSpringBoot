package com.shacks.graphqldemo.controller;

import com.shacks.graphqldemo.model.Player;
import com.shacks.graphqldemo.model.Team;
import com.shacks.graphqldemo.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayersController {
    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findById(@Argument Integer id) {
        return playerService.findOne(id);
    }

    @MutationMapping
    public Player addPlayer(@Argument String name, @Argument Team team) {
        return playerService.createPlayer(name, team);
    }

    @MutationMapping
    public Player deletePlayer(@Argument Integer id) {
        return playerService.deletePlayer(id);
    }
}
