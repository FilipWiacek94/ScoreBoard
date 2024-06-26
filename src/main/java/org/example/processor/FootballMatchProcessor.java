package org.example.processor;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;

import java.util.List;

public interface FootballMatchProcessor {

    /**
     * Method responsible for creating new football match.
     * @param homeTeam Name of a home team.
     * @param awayTeam Name of an away team.
     * @return Newly created FootballMatch object.
     */
    FootballMatch startFootballMatch(String homeTeam, String awayTeam);

    /**
     * Method responsible for updating score of the football match.
     * @param homeTeamNewScore Object containing name of a home team and a new score.
     * @param awayTeamNewScore Object containing name of an away team and a new score.
     * @return Updated football match object.
     */
    FootballMatch updateMatchScore(Pair<String, Integer> homeTeamNewScore, Pair<String, Integer> awayTeamNewScore);

    /**
     * Method responsible for finishing football match.
     * @param homeTeam Name of home team.
     * @param awayTeam Name of away team.
     * @return Finished FootballMatch.
     */
    FootballMatch finishMatch(String homeTeam, String awayTeam);

    /**
     * Method responsible for creating score summary of the football matches.
     * @return Created  score summary of the football matches.
     */
    List<FootballMatch> createMatchesSummary();
}

