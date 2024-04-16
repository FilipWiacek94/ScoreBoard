package org.example.processor;

import org.example.model.FootballMatch;
import org.example.repository.FootballMatchRepository;
import org.example.repository.impl.FootballMatchRepositoryImpl;

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
}
