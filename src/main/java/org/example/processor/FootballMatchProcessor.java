package org.example.processor;

import org.example.model.FootballMatch;

public interface FootballMatchProcessor {

    /**
     * Method responsible for creating new football match and storing it in the database.
     * @param homeTeam Name of a home team.
     * @param awayTeam Name of a away team.
     * @return Newly created FootballMatch object.
     */
    FootballMatch startFootballMatch(String homeTeam, String awayTeam);
}

