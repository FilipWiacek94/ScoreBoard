package processor;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;
import org.example.processor.FootballMatchProcessor;
import org.example.processor.impl.FootballMatchProcessorImpl;
import org.example.utils.MatchStatus;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FootballMatchProcessorTest {
    public static final String HOME_TEAM = "Arsenal";
    public static final String AWAY_TEAM = "Chelsea";

    @Test
    public void shouldStartNewFootballMatch() {
        //given
        FootballMatchProcessor processor = new FootballMatchProcessorImpl();

        //when
        FootballMatch startedFootballMatch = processor.startFootballMatch(HOME_TEAM, AWAY_TEAM);

        //then
        assertEquals(startedFootballMatch.getHomeTeamName(), HOME_TEAM);
        assertEquals(startedFootballMatch.getAwayTeamName(), AWAY_TEAM);
        assertEquals(startedFootballMatch.getAwayTeamScore(), Integer.valueOf(0));
        assertEquals(startedFootballMatch.getHomeTeamScore(), Integer.valueOf(0));
    }

    @Test
    public void shouldUpdateFootballMatchScore() {
        //given
        FootballMatchProcessor processor = new FootballMatchProcessorImpl();
        processor.startFootballMatch(HOME_TEAM, AWAY_TEAM);

        Pair<String, Integer> homeTeamNewScore = Pair.of(HOME_TEAM, 2);
        Pair<String, Integer> awayTeamNewScore = Pair.of(AWAY_TEAM, 1);

        //when
        FootballMatch updatedMatch = processor.updateMatchScore(homeTeamNewScore, awayTeamNewScore);

        //then
        assertEquals(updatedMatch.getHomeTeamName(), HOME_TEAM);
        assertEquals(updatedMatch.getAwayTeamName(), AWAY_TEAM);
        assertEquals(updatedMatch.getHomeTeamScore(), Integer.valueOf(2));
        assertEquals(updatedMatch.getAwayTeamScore(), Integer.valueOf(1));
        assertEquals(updatedMatch.getTotalScore(), Integer.valueOf(3));
    }

    @Test
    public void shouldFinishFootballMatch() {
        //given
        FootballMatchProcessor processor = new FootballMatchProcessorImpl();
        processor.startFootballMatch(HOME_TEAM, AWAY_TEAM);


        //when
        FootballMatch finishMatch = processor.finishMatch(HOME_TEAM, AWAY_TEAM);

        //then
        assertEquals(finishMatch.getHomeTeamName(), HOME_TEAM);
        assertEquals(finishMatch.getAwayTeamName(), AWAY_TEAM);
        assertEquals(finishMatch.getHomeTeamScore(), Integer.valueOf(0));
        assertEquals(finishMatch.getAwayTeamScore(), Integer.valueOf(0));
        assertEquals(finishMatch.getTotalScore(), Integer.valueOf(0));
        assertEquals(finishMatch.getMatchStatus(), MatchStatus.ENDED);
    }

    @Test
    public void shouldCreateFootballMatchesSummary() {
        //given
        FootballMatchProcessor processor = new FootballMatchProcessorImpl();
        processor.startFootballMatch(HOME_TEAM, "Bayern Monachium");
        processor.startFootballMatch("Manchester City", "Real Madryt");
        processor.startFootballMatch("Barcelona", "PSG");
        processor.startFootballMatch("Borussia Dortmund", "Atletico Madryt");


        FootballMatch oldestMatch = processor.updateMatchScore(new MutablePair<>("Borussia Dortmund", 3), new MutablePair<>("Atletico Madryt", 3));
        FootballMatch match1 = processor.updateMatchScore(new MutablePair<>(HOME_TEAM, 1), new MutablePair<>("Bayern Monachium", 1));
        FootballMatch match2 = processor.updateMatchScore(new MutablePair<>("Manchester City", 2), new MutablePair<>("Real Madryt", 2));
        FootballMatch match3 = processor.updateMatchScore(new MutablePair<>("Barcelona", 3), new MutablePair<>("PSG", 3));

        List<FootballMatch> summaryListCorrectOrder = List.of(oldestMatch, match3, match2, match1);

        //when
        List<FootballMatch> footballMatchesSummary = processor.createMatchesSummary();

        //then
        assertEquals(footballMatchesSummary, summaryListCorrectOrder);
        assertEquals(footballMatchesSummary.get(0).getTotalScore(), Integer.valueOf(6));
        assertEquals(footballMatchesSummary.get(0).getHomeTeamName(), HOME_TEAM);

    }
}
