package org.example.repository;

import org.example.model.FootballMatch;

import java.util.List;

public interface FootballMatchRepository {

    /**
     * Method responsible for storing new football match.
     * @param footballMatch which should be stored in the db.
     */
    FootballMatch startFootballMatch(final FootballMatch footballMatch);

    /**
     * Method responsible returning all existing football matches.
     * @return List of football matches.
     */
    List<FootballMatch> getFootballMatches();
}
