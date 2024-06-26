package org.example.processor.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.FootballMatch;
import org.example.processor.FootballMatchProcessor;
import org.example.repository.FootballMatchRepository;
import org.example.repository.impl.FootballMatchRepositoryImpl;

import java.util.List;

public class FootballMatchProcessorImpl implements FootballMatchProcessor {

    private final FootballMatchRepository footballMatchRepository;

    public FootballMatchProcessorImpl() {
        this.footballMatchRepository = new FootballMatchRepositoryImpl();
    }

    @Override
    public FootballMatch startFootballMatch(String homeTeam, String awayTeam) {
        FootballMatch newFootballMatch = FootballMatch.createNewFootballMatch(homeTeam, awayTeam);
        return footballMatchRepository.startFootballMatch(newFootballMatch);
    }

    @Override
    public FootballMatch updateMatchScore(Pair<String, Integer> homeTeamNewScore, Pair<String, Integer> awayTeamNewScore) {
        return footballMatchRepository.updateFootballMatch(homeTeamNewScore, awayTeamNewScore);
    }

    @Override
    public FootballMatch finishMatch(String homeTeam, String awayTeam) {
        return footballMatchRepository.finishMatch(homeTeam, awayTeam);
    }

    @Override
    public List<FootballMatch> createMatchesSummary() {
        return footballMatchRepository.createFootballMatchesSummary();
    }
}
