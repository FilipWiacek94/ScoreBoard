package org.example.repository;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;

import java.util.List;

public interface FootballMatchRepository {

    /**
     * Method responsible for storing new football match.
     * @param footballMatch which should be stored in the score board.
     */
    FootballMatch startFootballMatch(final FootballMatch footballMatch);

    /**
     * Method responsible for returning all existing football matches.
     * @return List of football matches.
     */
    List<FootballMatch> getFootballMatches();

    /**
     * Method responsible for updating football match and storing it in the score board.
     * @param homeTeamNewScore New score of a homeTeam
     * @param awayTeamNewScore New Score of a awayTeam
     * @return Updated FootballTeam
     */
    FootballMatch updateFootballMatch(final Pair<String, Integer> homeTeamNewScore, final Pair<String, Integer> awayTeamNewScore);

    /**
     * Method responsible for finishing football match and deleting it from the score board.
     * @param homeTeamName Name of home team.
     * @param awayTeamName Name of away team.
     * @return Finished FootballMatch.
     */
    FootballMatch finishMatch(final String homeTeamName, final String awayTeamName);

    /**
     * Method responsible for creating football matches summary in correct order.
     * @return created matches summary.
     */
    List<FootballMatch> createFootballMatchesSummary();
}
