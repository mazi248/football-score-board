package marta.playground.footballscoreboard.model;

import lombok.Getter;
import marta.playground.footballscoreboard.util.TimeUtils;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Match {
    private final UUID id;
    private final Instant startedDateTime;
    private final Team homeTeam;
    private final Team awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;

    public Match(Team homeTeam, Team awayTeam) {
        id = UUID.randomUUID();
        startedDateTime = TimeUtils.now();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        homeTeamScore = 0;
        awayTeamScore = 0;
    }

    public void updateScore(int homeTeamAbsoluteScore, int awayTeamAbsoluteScore) {
        homeTeamScore = homeTeamAbsoluteScore;
        awayTeamScore = awayTeamAbsoluteScore;
    }

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }
}
