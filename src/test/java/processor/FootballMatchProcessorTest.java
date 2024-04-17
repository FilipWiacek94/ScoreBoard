package processor;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;
import org.example.processor.FootballMatchProcessor;
import org.example.processor.impl.FootballMatchProcessorImpl;
import org.junit.Test;

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
}
