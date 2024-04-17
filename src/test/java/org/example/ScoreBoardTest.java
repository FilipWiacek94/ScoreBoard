package org.example;

import org.apache.commons.lang3.tuple.MutablePair;
import org.example.model.FootballMatch;
import org.example.processor.FootballMatchProcessor;
import org.example.processor.impl.FootballMatchProcessorImpl;
import org.example.utils.MatchStatus;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreBoardTest {
    public static final String BELGRANO = "Belgrano";
    public static final String RACING_CLUB = "Racing Club";

    public static final String BOCA_JUNIORS = "Boca Juniors";
    public static final String GODOY_CRUZ = "Godoy Cruise";

    public static final String LUNAS = "Lunas";
    public static final String ESTUDIANTES = "Estudiantes";


    @Test
    public void shouldSimulateScoreBoardAndMatchesLifecycle() {

        FootballMatchProcessor processor = new FootballMatchProcessorImpl();

        //Starting matches phase
        FootballMatch firstMatch = processor.startFootballMatch(BELGRANO, RACING_CLUB);
        FootballMatch secondMatch = processor.startFootballMatch(BOCA_JUNIORS, GODOY_CRUZ);
        FootballMatch thirdMatch = processor.startFootballMatch(LUNAS, ESTUDIANTES);

        assertEquals(firstMatch.getMatchStatus(), MatchStatus.IN_PROGRESS);
        assertEquals(secondMatch.getMatchStatus(), MatchStatus.IN_PROGRESS);
        assertEquals(thirdMatch.getMatchStatus(), MatchStatus.IN_PROGRESS);

        assertEquals(firstMatch.getTotalScore(), Integer.valueOf(0));
        assertEquals(secondMatch.getTotalScore(), Integer.valueOf(0));
        assertEquals(thirdMatch.getTotalScore(), Integer.valueOf(0));

        //First score updates phase
        FootballMatch firstMatchAfterUpdate =
                processor.updateMatchScore(new MutablePair<>(BELGRANO, 0), new MutablePair<>(RACING_CLUB, 4));

        FootballMatch secondMatchAfterUpdate =
                processor.updateMatchScore(new MutablePair<>(BOCA_JUNIORS, 1), new MutablePair<>(GODOY_CRUZ, 0));

        FootballMatch thirdMatchAfterUpdate =
                processor.updateMatchScore(new MutablePair<>(LUNAS, 1), new MutablePair<>(ESTUDIANTES, 2));

        List<FootballMatch> matchesSummaryAfterUpdate = processor.createMatchesSummary();
        List<FootballMatch> scoreBoardInCorrectOrder = Arrays.asList(firstMatchAfterUpdate, thirdMatchAfterUpdate, secondMatchAfterUpdate);

        assertEquals(firstMatchAfterUpdate.getTotalScore(), Integer.valueOf(4));
        assertEquals(secondMatchAfterUpdate.getTotalScore(), Integer.valueOf(1));
        assertEquals(thirdMatchAfterUpdate.getTotalScore(), Integer.valueOf(3));
        assertEquals(matchesSummaryAfterUpdate, scoreBoardInCorrectOrder);

        //Ending first match phase
        FootballMatch endedMatch = processor.finishMatch(BELGRANO, RACING_CLUB);
        assertEquals(endedMatch.getMatchStatus(), MatchStatus.ENDED);

        List<FootballMatch> matchesSummaryAfterFirstGameEnded = processor.createMatchesSummary();
        List<FootballMatch> scoreBoardInCorrectOrderAfterFirstGameEnded = Arrays.asList(thirdMatchAfterUpdate, secondMatchAfterUpdate);

        assertEquals(matchesSummaryAfterFirstGameEnded.size(), 2);
        assertEquals(matchesSummaryAfterFirstGameEnded, scoreBoardInCorrectOrderAfterFirstGameEnded);

        //Ending rest of matches
        FootballMatch secondEndedMatch = processor.finishMatch(BOCA_JUNIORS, GODOY_CRUZ);
        FootballMatch thirdEndedMatch = processor.finishMatch(LUNAS, ESTUDIANTES);
        assertEquals(secondEndedMatch.getMatchStatus(), MatchStatus.ENDED);
        assertEquals(thirdEndedMatch.getMatchStatus(), MatchStatus.ENDED);

        List<FootballMatch> matchesSummaryAfterAllEnded = processor.createMatchesSummary();
        assertEquals(matchesSummaryAfterAllEnded.size(), 0);

    }
}
