package org.example.repository.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;
import org.example.repository.FootballMatchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public FootballMatch finishMatch(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || awayTeamName == null) throw new IllegalArgumentException("homeTeamName or awayTeamName cannot be null");

        FootballMatch footballMatch = findFootballMatch(homeTeamName, awayTeamName);

        if (footballMatch == null) throw new IllegalStateException("footballMatch couldn't be find");

        getFootballMatches().remove(footballMatch);
        return footballMatch.finishGame();
    }

    @Override
    public List<FootballMatch> createFootballMatchesSummary() {
        List<FootballMatch> footballMatches = getFootballMatches();

        return footballMatches.stream().sorted((match1, match2) -> {
            if (match1.getTotalScore() != match2.getTotalScore()) {
                return Integer.compare(match2.getTotalScore(), match1.getTotalScore());
            } else {
                return match2.getStartTime().compareTo(match1.getStartTime());
            }
        }).collect(Collectors.toList());
    }

    private FootballMatch findFootballMatch(String homeTeamName, String awayTeamName) {
        return getFootballMatches().stream()
                .filter(match -> match.getHomeTeamName().equals(homeTeamName) && match.getAwayTeamName().equals(awayTeamName))
                .findFirst()
                .orElse(null);
    }
}
