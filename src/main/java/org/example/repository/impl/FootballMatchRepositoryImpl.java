package org.example.repository.impl;

import org.example.model.FootballMatch;
import org.example.repository.FootballMatchRepository;

import java.util.ArrayList;
import java.util.List;

public class FootballMatchRepositoryImpl implements FootballMatchRepository {

    private final List<FootballMatch> footballMatches;

    public FootballMatchRepositoryImpl() {
        this.footballMatches = new ArrayList<>();
    }

    @Override
    public FootballMatch startFootballMatch(FootballMatch footballMatch) {
        if (footballMatch == null) {
            throw new IllegalArgumentException("footballMatch cannot be null");
        }

        if (getFootballMatches().contains(footballMatch)) {
            throw new IllegalArgumentException("footballMatch already exists");
        }

        footballMatches.add(footballMatch);
        return footballMatch;
    }

    @Override
    public List<FootballMatch> getFootballMatches() {
        return footballMatches;
    }
}
