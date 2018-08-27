package model;

import java.util.Date;

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
