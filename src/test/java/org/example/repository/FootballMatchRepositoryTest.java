package org.example.repository;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;
import org.example.repository.impl.FootballMatchRepositoryImpl;
import org.example.utils.MatchStatus;
import org.junit.Test;

import java.util.Arrays;
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

    @Test
    public void shouldFinishFootballMatch() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();

        repository.startFootballMatch(newFootballMatch);

        //when
        FootballMatch endedFootballMatch = repository.finishMatch(HOME_TEAM, AWAY_TEAM);

        //then
        assertEquals(endedFootballMatch.getMatchStatus(), MatchStatus.ENDED);
        assertEquals(repository.getFootballMatches().size(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotFinishMatchDueToLackOfSpecificFootballMatch() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();

        repository.startFootballMatch(newFootballMatch);

        //when
        //then
        FootballMatch endedFootballMatch = repository.finishMatch("Tottenham", AWAY_TEAM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFinishMatchDueToNullValueOfHomeTeamName() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();

        repository.startFootballMatch(newFootballMatch);

        //when
        //then
        FootballMatch endedFootballMatch = repository.finishMatch(null, AWAY_TEAM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFinishMatchDueToNullValueOfAwayTeamName() {
        //given
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(HOME_TEAM, AWAY_TEAM);
        FootballMatchRepository repository = new FootballMatchRepositoryImpl();

        repository.startFootballMatch(newFootballMatch);

        //when
        //then
        FootballMatch endedFootballMatch = repository.finishMatch(HOME_TEAM, null);
    }

    @Test
    public void shouldReturnFootballMatchesSummaryWithNoSameTotalScore() {
        //given
        FootballMatch match1 = FootballMatch.createNewFootballMatch("Aston Villa", "Lille");
        FootballMatch match2 = FootballMatch.createNewFootballMatch("Club Brugge", "PAOK");
        FootballMatch match3 = FootballMatch.createNewFootballMatch("Olympiakos", "Fenerbahce");
        FootballMatch match4 = FootballMatch.createNewFootballMatch("Pilzno", "Fiorentina");

        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        repository.startFootballMatch(match1);
        repository.startFootballMatch(match2);
        repository.startFootballMatch(match3);
        repository.startFootballMatch(match4);

        repository.updateFootballMatch(new MutablePair<>("Aston Villa", 1), new MutablePair<>("Lille", 1));
        repository.updateFootballMatch(new MutablePair<>("Club Brugge", 2), new MutablePair<>("PAOK", 2));
        repository.updateFootballMatch(new MutablePair<>("Olympiakos", 3), new MutablePair<>("Fenerbahce", 3));
        repository.updateFootballMatch(new MutablePair<>("Pilzno", 4), new MutablePair<>("Fiorentina", 4));

        List<FootballMatch> footballMatchesSummaryInCorrectOrder = Arrays.asList(match4, match3, match2, match1);

        //when
        List<FootballMatch> footballMatchesSummary = repository.createFootballMatachesSummary();

        //then
        assertEquals(footballMatchesSummary, footballMatchesSummaryInCorrectOrder);
        assertEquals(footballMatchesSummary.get(0).getTotalScore(), Integer.valueOf(8));
    }

    @Test
    public void shouldReturnFootballMatchesSummaryWithSameTotalScore() {
        //given
        FootballMatch oldestMatch = FootballMatch.createNewFootballMatch("Pilzno", "Fiorentina");
        FootballMatch match1 = FootballMatch.createNewFootballMatch("Aston Villa", "Lille");
        FootballMatch match2 = FootballMatch.createNewFootballMatch("Club Brugge", "PAOK");
        FootballMatch match3 = FootballMatch.createNewFootballMatch("Olympiakos", "Fenerbahce");


        FootballMatchRepository repository = new FootballMatchRepositoryImpl();
        repository.startFootballMatch(oldestMatch);
        repository.startFootballMatch(match1);
        repository.startFootballMatch(match2);
        repository.startFootballMatch(match3);

        repository.updateFootballMatch(new MutablePair<>("Aston Villa", 1), new MutablePair<>("Lille", 1));
        repository.updateFootballMatch(new MutablePair<>("Club Brugge", 2), new MutablePair<>("PAOK", 2));
        repository.updateFootballMatch(new MutablePair<>("Olympiakos", 3), new MutablePair<>("Fenerbahce", 3));
        repository.updateFootballMatch(new MutablePair<>("Pilzno", 3), new MutablePair<>("Fiorentina", 3));

        List<FootballMatch> footballMatchesSummaryInCorrectOrder = Arrays.asList(oldestMatch, match3, match2, match1);

        //when
        List<FootballMatch> footballMatchesSummary = repository.createFootballMatachesSummary();

        //then
        assertEquals(footballMatchesSummary, footballMatchesSummaryInCorrectOrder);
        assertEquals(footballMatchesSummary.get(0).getTotalScore(), Integer.valueOf(6));
    }
}
