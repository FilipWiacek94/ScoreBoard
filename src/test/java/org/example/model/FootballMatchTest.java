package org.example.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
