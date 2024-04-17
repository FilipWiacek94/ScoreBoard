package org.example.repository;

import org.apache.commons.lang3.tuple.Pair;
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

    @Test
    public void shouldUpdateFootballMatch() {
        //given
        FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
    }

    @Test
    public void shouldUpdateFootballMatchScore() {
        //given
        Pair<String, Integer> homeTeamNewScore = Pair.of(HOME_TEAM, 2);
        Pair<String, Integer> awayTeamNewScore = Pair.of(AWAY_TEAM, 1);

        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        repository.startFootballMatch(newFootballMatch);

        //when
        FootballMatch updateFootballMatch = repository.updateFootballMatch(homeTeamNewScore, awayTeamNewScore);

        //then
        assertEquals(repository.getFootballMatches().size(), 1);
        assertEquals(updateFootballMatch.getHomeTeamName(), HOME_TEAM);
        assertEquals(updateFootballMatch.getAwayTeamName(), AWAY_TEAM);
        assertEquals(repository.getFootballMatches().get(0).getTotalScore(), Integer.valueOf(3));
        assertEquals(repository.getFootballMatches().get(0).getHomeTeamScore(), Integer.valueOf(2));
        assertEquals(repository.getFootballMatches().get(0).getAwayTeamScore(), Integer.valueOf(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateFootballMatchDueToWrongHomeTeam() {
        //given
        Pair<String, Integer> homeTeamNewScore = Pair.of("Tottenham", 2);
        Pair<String, Integer> awayTeamNewScore = Pair.of(AWAY_TEAM, 1);

        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        repository.startFootballMatch(newFootballMatch);

        //when
        //then
        repository.updateFootballMatch(homeTeamNewScore, awayTeamNewScore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateFootballMatchDueToWrongAwayTeam() {
        //given
        Pair<String, Integer> homeTeamNewScore = Pair.of(HOME_TEAM, 2);
        Pair<String, Integer> awayTeamNewScore = Pair.of("Tottenham", 1);

        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        repository.startFootballMatch(newFootballMatch);

        //when
        //then
        repository.updateFootballMatch(homeTeamNewScore, awayTeamNewScore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateFootballMatchDueToNullValueOfHomeTeamNewScore() {
        //given
        Pair<String, Integer> awayTeamNewScore = Pair.of(AWAY_TEAM, 1);

        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        repository.startFootballMatch(newFootballMatch);

        //when
        //then
        repository.updateFootballMatch(null, awayTeamNewScore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateFootballMatchDueToNullValueOfAwayTeamNewScore() {
        //given
        Pair<String, Integer> homeTeamNewScore = Pair.of(HOME_TEAM, 1);

        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        repository.startFootballMatch(newFootballMatch);

        //when
        //then
        repository.updateFootballMatch(homeTeamNewScore, null);
    }
}
