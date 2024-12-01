package marta.playground.footballscoreboard.model;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Match {
    private UUID id;

    private Instant startedDateTime;
    private Instant updatedDateTime;

    private Team homeTeam;
    private int homeTeamScore;
    private Team awayTeam;
    private int awayTeamScore;
}
