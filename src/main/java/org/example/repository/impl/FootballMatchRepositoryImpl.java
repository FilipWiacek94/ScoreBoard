package org.example.repository.impl;

import org.apache.commons.lang3.tuple.Pair;
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

    @Override
    public FootballMatch updateFootballMatch(Pair<String, Integer> homeTeamNewScore, Pair<String, Integer> awayTeamNewScore) {
        if (homeTeamNewScore == null || awayTeamNewScore == null) throw new IllegalArgumentException("homeTeamNewScore or awayTeamNewScore cannot be null");

        FootballMatch footballMatch = findFootballMatch(homeTeamNewScore.getLeft(), awayTeamNewScore.getLeft());

        if (footballMatch == null) throw new IllegalArgumentException("footballMatch couldn't be find");

        return footballMatch.updateScore(homeTeamNewScore, awayTeamNewScore);
    }

    private FootballMatch findFootballMatch(String homeTeamName, String awayTeamName) {
        return getFootballMatches().stream()
                .filter(match -> match.getHomeTeamName().equals(homeTeamName) && match.getAwayTeamName().equals(awayTeamName))
                .findFirst()
                .orElse(null);
    }
}
