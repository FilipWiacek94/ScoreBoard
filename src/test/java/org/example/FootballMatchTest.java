package org.example;

import org.junit.Test;

public class FootballMatchTest {
    public static final String HOME_TEAM = "Arsenal";
    public static final String AWAY_TEAM = "Chelsea";

    @Test
    public void shouldCreateFootballMatch() {
        //given
        //when
        FootballMatch match = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        //then
        assertEquals(match.getHomeTeamName, HOME_TEAM);
        assertEquals(match.getAwayTeamName, AWAY_TEAM);
        assertEquals(match.getHomeTeamScore, 0);
        assertEquals(match.getAwayTeamScore, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToLackOfHomeTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch(null, AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToLackOfAwayTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch(HOME_TEAM, null);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToTooLongHomeTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch("test_test_test_test_test_test_test_test_test_test_test_test_test_", AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToTooLongAwayTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch(HOME_TEAM, "test_test_test_test_test_test_test_test_test_test_test_test_test_");
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToNumbersInHomeTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch("Ar123senal", AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToNumbersInAwayTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch(HOME_TEAM, "Che333lsea");
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToEmptyHomeTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch("", AWAY_TEAM);
        //when
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFootballMatchDueToEmptyAwayTeamName() {
        //given
        FootballMatch match = FootballMatch.createNewFootballMatch(HOME_TEAM, "");
        //when
        //then
    }
}
