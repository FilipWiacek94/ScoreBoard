package org.example.repository;

import org.example.model.FootballMatch;
import org.example.repository.impl.FootballMatchRepositoryImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FootballMatchRepositoryTest {
    public static final String HOME_TEAM = "Arsenal";
    public static final String AWAY_TEAM = "Chelsea";

    @Test
    public void shouldStoreNewFootballMatchInDatabase() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();

        //when
        repository.startFootballMatch(newFootballMatch);

        //then
        List<FootballMatch> footballMatches = repository.getFootballMatches();
        assertEquals(footballMatches.size(), 1);
        assertEquals(footballMatches.get(0).getHomeTeamName(), HOME_TEAM);
        assertEquals(footballMatches.get(0).getAwayTeamName(), AWAY_TEAM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotStoreNewFootballMatchDutToNullValueOfFootballMatch() {
        //given
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        //when
        //then
        repository.startFootballMatch(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotStoreNewFootballMatchDutToEqualTeams() {
        //given
        FootballMatch newFootballMatch1 = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatch newFootballMatch2 = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        //when
        //then
        repository.startFootballMatch(newFootballMatch1);
        repository.startFootballMatch(newFootballMatch2);
    }

    @Test
    public void shouldReturnAllFootballMatches() {
        //given
        FootballMatch newFootballMatch1 = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatch newFootballMatch2 = FootballMatch.createNewFootballMatch("Borussia Dortmund", "Bayer Leverkusen");
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();

        //when
        repository.startFootballMatch(newFootballMatch1);
        repository.startFootballMatch(newFootballMatch2);

        //then
        assertEquals(repository.getFootballMatches().size(), 2);
    }
}
