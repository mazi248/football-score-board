package marta.playground.footballscoreboard.model;

import lombok.Getter;

@Getter
public class Team {
    private final String name;

    public Team(String name) {
        this.name = name;
    }
}
