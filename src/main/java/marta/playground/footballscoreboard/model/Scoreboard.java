package marta.playground.footballscoreboard.model;

import java.util.*;

public class Scoreboard {
    private final Map<UUID, Match> matches = new HashMap<>();

    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        Match newMatch = new Match(homeTeam, awayTeam);
        matches.put(newMatch.getId(), newMatch);
        return newMatch;
    }

    public void updateScore(UUID matchId, int homeTeamAbsoluteScore, int awayTeamAbsoluteScore) {
        Optional.ofNullable(matches.get(matchId))
            .orElseThrow(() -> new IllegalArgumentException("Match not found"))
            .updateScore(homeTeamAbsoluteScore, awayTeamAbsoluteScore);
    }

    public void endMatch(UUID matchId) {
        if (matches.containsKey(matchId)) {
            matches.remove(matchId);
        } else {
            throw new IllegalArgumentException("Match not found");
        }
    }

    public List<Match> getSummary() {
        return matches.values().stream()
            .sorted((match1, match2) -> {
                int scoreCompareResult = Integer.compare(match2.getTotalScore(), match1.getTotalScore());
                if (scoreCompareResult == 0) {
                    return match2.getStartedDateTime().compareTo(match1.getStartedDateTime());
                } else {
                    return scoreCompareResult;
                }
            })
            .toList();
    }
}
