package org.example.model;


import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.example.utils.MatchStatus;

import java.util.Objects;

public class FootballMatch {

    private final Pair<String, Integer> homeTeam;
    private final Pair<String, Integer> awayTeam;
    private final Integer totalScore;
    private final MatchStatus matchStatus;

    private FootballMatch(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || awayTeamName == null) throw new IllegalArgumentException("homeTeamName and awayTeamName cannot be null");
        if (homeTeamName.length() > 50 || awayTeamName.length() > 50) throw new IllegalArgumentException("homeTeamName and awayTeamName cannot be longer than 50 characters");
        if (!homeTeamName.matches ("^[a-zA-Z ]+$") || !awayTeamName.matches ("^[a-zA-Z ]+$")) throw new IllegalArgumentException("homeTeamName and awayTeamName should contains only letters");
        if (homeTeamName.isEmpty() || awayTeamName.isEmpty()) throw new IllegalArgumentException("homeTeamName and awayTeamName should not be empty");

        this.homeTeam = new MutablePair<>(homeTeamName, 0);
        this.awayTeam = new MutablePair<>(awayTeamName, 0);
        this.totalScore = 0;
        this.matchStatus = MatchStatus.IN_PROGRESS;
    }


    public static FootballMatch createNewFootballMatch(String homeTeamName, String awayTeamName) {
        return new FootballMatch(homeTeamName, awayTeamName);
    }

    public String getHomeTeamName() {
        return this.homeTeam.getKey();
    }

    public String getAwayTeamName() {
        return this.awayTeam.getKey();
    }

    public Integer getHomeTeamScore() {
        return this.homeTeam.getValue();
    }

    public Integer getAwayTeamScore() {
        return this.awayTeam.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballMatch that = (FootballMatch) o;
        return Objects.equals(homeTeam, that.homeTeam) && Objects.equals(awayTeam, that.awayTeam) && Objects.equals(totalScore, that.totalScore) && matchStatus == that.matchStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, totalScore, matchStatus);
    }
}
