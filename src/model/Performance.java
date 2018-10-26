package model;

/**
 * Created by nick on 9/3/18.
 */
public class Performance {
    private int playerId;
    private Player player;
    private int teamId;
    private Team team;
    private int matchId;
    private Match match;
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

    public Performance() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setAdr(double adr) {
        this.adr = adr;
    }

    public void setKast(double kast) {
        this.kast = kast;
    }

    public void setRating(double rating) {
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
