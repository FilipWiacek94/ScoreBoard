package org.example.model;

import org.apache.commons.lang3.tuple.MutablePair;
import org.example.utils.MatchStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FootballMatchTest {
    public static final String HOME_TEAM = "Arsenal";
    public static final String AWAY_TEAM = "Chelsea";

    @Test
    public void shouldCreateFootballMatch() {
        //given

        //when
        FootballMatch match = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //then
        assertEquals(match.getHomeTeamName(), HOME_TEAM);
        assertEquals(match.getAwayTeamName(), AWAY_TEAM);
        assertEquals(match.getHomeTeamScore(), Integer.valueOf(0));
        assertEquals(match.getAwayTeamScore(), Integer.valueOf(0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToLackOfHomeTeamName() {
        //given
        FootballMatch.createNewFootballMatch(null, AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToLackOfAwayTeamName() {
        //given
        FootballMatch.createNewFootballMatch(HOME_TEAM, null);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToTooLongHomeTeamName() {
        //given
        FootballMatch.createNewFootballMatch("test_test_test_test_test_test_test_test_test_test_test_test_test_", AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToTooLongAwayTeamName() {
        //given
        FootballMatch.createNewFootballMatch(HOME_TEAM, "test_test_test_test_test_test_test_test_test_test_test_test_test_");
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToNumbersInHomeTeamName() {
        //given
        FootballMatch.createNewFootballMatch("Ar123senal", AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToNumbersInAwayTeamName() {
        //given
        FootballMatch.createNewFootballMatch(HOME_TEAM, "Che333lsea");
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToEmptyHomeTeamName() {
        //given
        FootballMatch.createNewFootballMatch("", AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToEmptyAwayTeamName() {
        //given
        FootballMatch.createNewFootballMatch(HOME_TEAM, "");
        //when
        //then
    }

    @Test
    public void shouldUpdateMatchScore() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //when
        newFootballMatch.updateScore(new MutablePair<>(HOME_TEAM, 2), new MutablePair<>(AWAY_TEAM, 1));

        //then
        assertEquals(newFootballMatch.getHomeTeamScore(), Integer.valueOf(2));
        assertEquals(newFootballMatch.getAwayTeamScore(), Integer.valueOf(1));
        assertEquals(newFootballMatch.getTotalScore(), Integer.valueOf(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateMatchScoreDueToWrongHomeTeamName() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //when
        newFootballMatch.updateScore(new MutablePair<>("Tottenham", 2), new MutablePair<>(AWAY_TEAM, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateMatchScoreDueToWrongAwayTeamName() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //when
        newFootballMatch.updateScore(new MutablePair<>(HOME_TEAM, 2), new MutablePair<>("Tottenham", 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateMatchSCoreDueToHomeTeamNewScoreBelowZero() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //when
        newFootballMatch.updateScore(new MutablePair<>(HOME_TEAM, -2), new MutablePair<>(AWAY_TEAM, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateMatchSCoreDueToAwayTeamNewScoreBelowZero() {
        //given
        var newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //when
        newFootballMatch.updateScore(new MutablePair<>(HOME_TEAM, 2), new MutablePair<>(AWAY_TEAM, -1));
    }

    @Test
    public void shouldFinishMatch() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //when
        newFootballMatch.finishGame();

        //then
        assertEquals(newFootballMatch.getMatchStatus(), MatchStatus.ENDED);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotFinishMatchDueToAlreadyEndedStatus() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        newFootballMatch.finishGame();

        //when
        //then
        newFootballMatch.finishGame();
    }

    @Test
    public void shouldStartMatchTime() {
        //given
        //when
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);

        //then
        assertNotNull(newFootballMatch.getStartTime());
    }
}
