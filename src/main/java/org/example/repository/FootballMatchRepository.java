package org.example.repository;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;

import java.util.List;

public interface FootballMatchRepository {

    /**
     * Method responsible for storing new football match.
     * @param footballMatch which should be stored in the db.
     */
    FootballMatch startFootballMatch(final FootballMatch footballMatch);

    /**
     * Method responsible for returning all existing football matches.
     * @return List of football matches.
     */
    List<FootballMatch> getFootballMatches();

    /**
     * Method responsible for updating football match and storing it in the db.
     * @param homeTeamNewScore New score of a homeTeam
     * @param awayTeamNewScore New Score of a awayTeam
     * @return Updated FootballTeam
     */
    FootballMatch updateFootballMatch(final Pair<String, Integer> homeTeamNewScore, final Pair<String, Integer> awayTeamNewScore);
}
