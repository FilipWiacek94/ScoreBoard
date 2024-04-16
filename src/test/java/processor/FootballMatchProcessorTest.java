package processor;

import org.example.model.FootballMatch;
import org.example.processor.FootballMatchProcessor;
import org.example.processor.FootballMatchProcessorImpl;
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
}
