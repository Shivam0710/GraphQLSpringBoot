package com.shacks.graphqldemo.service;

import com.shacks.graphqldemo.model.Player;
import com.shacks.graphqldemo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    private List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger();

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findOne(Integer id) {
        return players.stream().filter(player -> Objects.equals(player.id(), id)).findFirst();
    }

    public Player createPlayer(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player deletePlayer(Integer id) {
        Player player = players.stream().filter(p -> Objects.equals(id, p.id())).findFirst().orElseThrow(IllegalArgumentException::new);
        players.remove(player);
        return player;
    }

    public Player updatePlayer(Integer id, String name, Team team) {
        Player player = new Player(id, name, team);
        Optional<Player> playerOptional = players.stream().filter(p -> p.id() == id).findFirst();

        if(playerOptional.isPresent()) {
            Player pp = playerOptional.get();
            int index = players.indexOf(pp);
            players.set(index, player);
        } else {
            throw new IllegalArgumentException();
        }

        return player;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Bumrah", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Virat", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "DK", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "Shreyas", Team.KKR));
        players.add(new Player(id.incrementAndGet(), "Chahal", Team.RR));
        players.add(new Player(id.incrementAndGet(), "Head", Team.SRH));
    }
}
