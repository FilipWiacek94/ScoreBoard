package org.example.model;


import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.example.utils.MatchStatus;
import org.joda.time.Instant;

import java.util.Objects;

public class FootballMatch {

    private final Pair<String, Integer> homeTeam;
    private final Pair<String, Integer> awayTeam;
    private Integer totalScore;
    private MatchStatus matchStatus;
    private final Instant startTime;

    private FootballMatch(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || awayTeamName == null) throw new IllegalArgumentException("homeTeamName and awayTeamName cannot be null");
        if (homeTeamName.length() > 50 || awayTeamName.length() > 50) throw new IllegalArgumentException("homeTeamName and awayTeamName cannot be longer than 50 characters");
        if (!homeTeamName.matches ("^[a-zA-Z ]+$") || !awayTeamName.matches ("^[a-zA-Z ]+$")) throw new IllegalArgumentException("homeTeamName and awayTeamName should contains only letters");
        if (homeTeamName.isEmpty() || awayTeamName.isEmpty()) throw new IllegalArgumentException("homeTeamName and awayTeamName should not be empty");

        this.homeTeam = new MutablePair<>(homeTeamName, 0);
        this.awayTeam = new MutablePair<>(awayTeamName, 0);
        this.totalScore = 0;
        this.matchStatus = MatchStatus.IN_PROGRESS;
        this.startTime = Instant.now();
    }

    public static FootballMatch createNewFootballMatch(String homeTeamName, String awayTeamName) {
        return new FootballMatch(homeTeamName, awayTeamName);
    }

    public FootballMatch updateScore(Pair<String, Integer> homeTeamNewScore, Pair<String, Integer> awayTeamNewScore) {
        if (areFootballTeamsNamesNotSame(homeTeamNewScore.getKey(), awayTeamNewScore.getKey())) throw new IllegalArgumentException("homeTeam or awayTeam are not the same");
        if (homeTeamNewScore.getValue() < 0 || awayTeamNewScore.getValue() < 0) throw new IllegalArgumentException("homeTeam new score or awayTeam new score is below 0 ");

        this.homeTeam.setValue(homeTeamNewScore.getValue());
        this.awayTeam.setValue(awayTeamNewScore.getValue());
        this.totalScore = this.homeTeam.getValue() + this.awayTeam.getValue();
        return this;
    }

    public FootballMatch finishGame() {
        if (this.matchStatus.equals(MatchStatus.ENDED)) throw new IllegalStateException("Football match already has status ENDED.");

        this.matchStatus = MatchStatus.ENDED;
        return this;
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

    public Integer getTotalScore() {
        return this.totalScore;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public Instant getStartTime() {
        return startTime;
    }

    private boolean areFootballTeamsNamesNotSame(String homeTeamName, String awayTeamName) {
        return !this.homeTeam.getKey().equals(homeTeamName) || !this.awayTeam.getKey().equals(awayTeamName);
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
