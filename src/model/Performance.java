package model;

/**
 * Created by nick on 9/3/18.
 */
public class Performance {
    private int playerId;
    private int teamId;
    private int matchId;
    private int kills;
    private int deaths;
    private double adr;
    private double kast;
    private double rating;

    public Performance(int playerId, int teamId, int matchId, int kills, int deaths, double adr, double kast, double rating) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.matchId = matchId;
        this.kills = kills;
        this.deaths = deaths;
        this.adr = adr;
        this.kast = kast;
        this.rating = rating;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public double getAdr() {
        return adr;
    }

    public double getKast() {
        return kast;
    }

    public double getRating() {
        return rating;
    }
}
