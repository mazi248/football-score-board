package marta.playground.footballscoreboard.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Scoreboard {
    private final Collection<Match> matches = new LinkedList<>();

    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        //TODO implement
        return null;
    }

    public void updateScore(UUID matchId, int homeTeamAbsoluteScore, int awayTeamAbsoluteScore) {
        //TODO implement
    }

    public void endMatch(UUID matchId) {
        //TODO implement
    }

    public List<Match> getSummary() {
        //TODO implement
        return null;
    }
}
