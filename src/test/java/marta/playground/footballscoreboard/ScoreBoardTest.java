package marta.playground.footballscoreboard;

import marta.playground.factory.TeamFactory;
import marta.playground.footballscoreboard.model.Match;
import marta.playground.footballscoreboard.model.Scoreboard;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@Disabled //TODO enable after implementation
class ScoreBoardTest {

    @Test
    void test_getSummary_returnsMatchesInCorrectOrder() {
        //GIVEN
        Scoreboard scoreboard = new Scoreboard();

        Match match1 = scoreboard.startNewMatch(TeamFactory.createTeam("Mexico"), TeamFactory.createTeam("Canada"));
        Match match2 = scoreboard.startNewMatch(TeamFactory.createTeam("Spain"), TeamFactory.createTeam("Brazil"));
        Match match3 = scoreboard.startNewMatch(TeamFactory.createTeam("Germany"), TeamFactory.createTeam("France"));
        Match match4 = scoreboard.startNewMatch(TeamFactory.createTeam("Uruguay"), TeamFactory.createTeam("Italy"));
        Match match5 = scoreboard.startNewMatch(TeamFactory.createTeam("Argentina"), TeamFactory.createTeam("Australia"));

        //WHEN
        scoreboard.updateScore(match1.getId(), 0, 5);
        scoreboard.updateScore(match2.getId(), 10, 2);
        scoreboard.updateScore(match3.getId(), 2, 2);
        scoreboard.updateScore(match4.getId(), 6, 6);
        scoreboard.updateScore(match5.getId(), 3, 1);

        //THEN
        assertThat(scoreboard.getSummary())
            .extracting(match -> match.getHomeTeam().getName(), Match::getHomeTeamScore,
                        match -> match.getAwayTeam().getName(), Match::getAwayTeamScore)
            .containsExactly(
                tuple("Uruguay", 6, "Italy", 6),
                tuple("Spain", 10, "Brazil", 2),
                tuple("Mexico", 0, "Canada", 5),
                tuple("Argentina", 3, "Australia", 1),
                tuple("Germany", 2, "France", 2)
            );
    }

    @Test
    void test_startMatch_setsScoreTo_0_0() {
        //GIVEN
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startNewMatch(TeamFactory.createTeam("Mexico"), TeamFactory.createTeam("Canada"));

        //THEN
        assertThat(scoreboard.getSummary())
            .extracting(match -> match.getHomeTeam().getName(), Match::getHomeTeamScore,
                        match -> match.getAwayTeam().getName(), Match::getAwayTeamScore)
            .containsExactly(
                tuple("Mexico", 0, "Canada", 0)
            );
    }

    @Test
    void test_updateScore_correctlyUpdatesScore_andOnlyForRequestedMatch() {
        //GIVEN
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startNewMatch(TeamFactory.createTeam("Mexico"), TeamFactory.createTeam("Canada"));
        Match match2 = scoreboard.startNewMatch(TeamFactory.createTeam("Spain"), TeamFactory.createTeam("Brazil"));
        scoreboard.startNewMatch(TeamFactory.createTeam("Germany"), TeamFactory.createTeam("France"));

        //WHEN
        scoreboard.updateScore(match2.getId(), 20, 10);

        //THEN
        assertThat(scoreboard.getSummary())
            .extracting(match -> match.getHomeTeam().getName(), Match::getHomeTeamScore,
                        match -> match.getAwayTeam().getName(), Match::getAwayTeamScore)
            .containsExactlyInAnyOrder(
                tuple("Spain", 20, "Brazil", 10),
                tuple("Mexico", 0, "Canada", 0),
                tuple("Germany", 0, "France", 0)
            );
    }

    @Test
    void test_endMatch_removesMatchFromScoreBoard() {
        //GIVEN
        Scoreboard scoreboard = new Scoreboard();

        Match match1 = scoreboard.startNewMatch(TeamFactory.createTeam("Mexico"), TeamFactory.createTeam("Canada"));
        scoreboard.startNewMatch(TeamFactory.createTeam("Spain"), TeamFactory.createTeam("Brazil"));
        scoreboard.startNewMatch(TeamFactory.createTeam("Germany"), TeamFactory.createTeam("France"));

        //WHEN
        scoreboard.endMatch(match1.getId());

        //THEN
        assertThat(scoreboard.getSummary())
            .extracting(match -> match.getHomeTeam().getName(), match -> match.getAwayTeam().getName())
            .containsExactlyInAnyOrder(
                tuple("Spain", "Brazil"),
                tuple("Germany", "France")
            );
    }

    @Test
    void test_updateScoreOfInvalidMatchShouldResultInException() {
        //GIVEN
        Scoreboard scoreboard = new Scoreboard();

        UUID uuidOfNotExistingMatch = UUID.randomUUID();

        // WHEN / THEN
        assertThatThrownBy(() -> scoreboard.updateScore(uuidOfNotExistingMatch, 0, 5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("MatchId not found");
    }

    @Test
    void test_updateScoreOfEndedMatchShouldResultInException() {
        //GIVEN
        Scoreboard scoreboard = new Scoreboard();

        Match match1 = scoreboard.startNewMatch(TeamFactory.createTeam("Mexico"), TeamFactory.createTeam("Canada"));
        scoreboard.endMatch(match1.getId());

        // WHEN / THEN
        assertThatThrownBy(() -> scoreboard.updateScore(match1.getId(), 0, 5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("MatchId not found");
    }

    @Test
    void test_endMatchOfInvalidMatchShouldResultInException() {
        //GIVEN
        Scoreboard scoreboard = new Scoreboard();

        UUID uuidOfNotExistingMatch = UUID.randomUUID();

        // WHEN / THEN
        assertThatThrownBy(() -> scoreboard.endMatch(uuidOfNotExistingMatch))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("MatchId not found");
    }
}