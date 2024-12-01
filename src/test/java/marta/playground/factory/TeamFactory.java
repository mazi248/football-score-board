package marta.playground.factory;

import marta.playground.footballscoreboard.model.Team;

public class TeamFactory {
    public static Team createTeam(String teamName) {
        return new Team(teamName);
    }
}
