package model;

import java.sql.Date;

public class Match {
    private int id;
    private String url;
    private int homeId;
    private int homeScore;
    private int awayId;
    private int awayScore;
    private String eventUrl;
    private int bestOf;
    private Date date;
    private Team homeTeam;
    private Team awayTeam;

    public Match(int id, String url, int homeId, int homeScore, int awayId, int awayScore, String eventUrl, int bestOf, Date date) {
        this.id = id;
        this.url = url;
        this.homeId = homeId;
        this.homeScore = homeScore;
        this.awayId = awayId;
        this.awayScore = awayScore;
        this.eventUrl = eventUrl;
        this.bestOf = bestOf;
        this.date = date;
    }

    public Match() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayId(int awayId) {
        this.awayId = awayId;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public void setBestOf(int bestOf) {
        this.bestOf = bestOf;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getHomeId() {
        return homeId;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayId() {
        return awayId;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public int getBestOf() {
        return bestOf;
    }

    public Date getDate() {
        return date;
    }
}
