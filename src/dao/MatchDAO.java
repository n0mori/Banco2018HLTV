package dao;

import model.Match;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 8/28/18.
 */
public class MatchDAO extends DAO<Match> {

    private static final String CREATE_QUERY
            = "INSERT INTO hltv.match"
            + "(id, url, home_id, home_score, away_id, away_score, event_url, bestof, \"date\")"
            + "VALUES (?,?,?,?,?,?,?,?,?) "
            + "ON CONFLICT ON CONSTRAINT match_pkey DO UPDATE SET "
            + "url = ?, home_id = ?, home_score = ?, away_id = ?, away_score = ?, event_url = ?, bestof = ?, \"date\" = ? "
            + "WHERE hltv.match.id = ?;";

    private static final String READ_QUERY
            = "SELECT hltv.match.id as id, hltv.match.url as url, home_id, home_score, away_id, away_score, event_url, bestof, \"date\", h.name as hname, a.name as aname "
            + "FROM hltv.match "
            + "INNER JOIN hltv.team as h ON h.id = home_id "
            + "INNER JOIN hltv.team as a ON a.id = away_id "
            + "WHERE hltv.match.id = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE hltv.match "
            + "SET url = ?, home_id = ?, home_score = ?, away_id = ?, away_score = ?, event_url = ?, bestof = ?, \"date\" = ? "
            + "WHERE id = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM hltv.match "
            + "WHERE id = ?;";

    private static final String ALL_QUERY
            = "SELECT hltv.match.id as id, hltv.match.url as url, home_id, home_score, away_id, away_score, event_url, bestof, \"date\", h.name as hname, a.name as aname "
            + "FROM hltv.match "
            + "INNER JOIN hltv.team as h ON h.id = home_id "
            + "INNER JOIN hltv.team as a ON a.id = away_id "
            + "ORDER BY \"date\" DESC;";

    private static final String HISTORY_QUERY
            = "SELECT hltv.match.id as id, hltv.match.url as url, home_id, home_score, away_id, away_score, event_url, bestof, \"date\", h.name as hname, a.name as aname "
            + "FROM hltv.match "
            + "INNER JOIN hltv.team as h ON h.id = home_id "
            + "INNER JOIN hltv.team as a ON a.id = away_id "
            + "WHERE (home_id = ? and away_id = ?) OR (away_id = ? and home_id = ?) "
            + "ORDER BY \"date\" DESC;";

    private static final String LAST_MATCH
            = "SELECT hltv.match.id as id, hltv.match.url as url, home_id, home_score, away_id, away_score, event_url, bestof, \"date\", h.name as hname, a.name as aname "
            + "FROM hltv.match "
            + "INNER JOIN hltv.team as h ON h.id = home_id "
            + "INNER JOIN hltv.team as a ON a.id = away_id "
            + "WHERE (home_id = ?) OR (away_id = ?) "
            + "ORDER BY \"date\" DESC "
            + "LIMIT 1;";

    private static final String WIN_COUNT
            = "SELECT COUNT(*) as cnt "
            + "FROM hltv.match "
            + "WHERE (home_id = ? and home_score > away_score) OR (away_id = ? and away_score > home_score) ";

    private static final String TEAM_MATCH_COUNT
            = "SELECT COUNT(*) as cnt "
            + "FROM hltv.match "
            + "WHERE home_id = ? or away_id = ?;";

    @Override
    public void create(Match match) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
            statement.setInt(1, match.getId());
            statement.setString(2, match.getUrl());
            statement.setInt(3, match.getHomeId());
            statement.setInt(4, match.getHomeScore());
            statement.setInt(5, match.getAwayId());
            statement.setInt(6, match.getAwayScore());
            statement.setString(7, match.getEventUrl());
            statement.setInt(8, match.getBestOf());
            statement.setDate(9, match.getDate());
            statement.setString(10, match.getUrl());
            statement.setInt(11, match.getHomeId());
            statement.setInt(12, match.getHomeScore());
            statement.setInt(13, match.getAwayId());
            statement.setInt(14, match.getAwayScore());
            statement.setString(15, match.getEventUrl());
            statement.setInt(16, match.getBestOf());
            statement.setDate(17, match.getDate());
            statement.setInt(18, match.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.err.println("Erro:" + ex.getMessage());

            throw new SQLException("Erro ao criar match");
        }

    }

    @Override
    public Match read(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Match m = new Match(
                            result.getInt("id"),
                            result.getString("url"),
                            result.getInt("home_id"),
                            result.getInt("home_score"),
                            result.getInt("away_id"),
                            result.getInt("away_score"),
                            result.getString("event_url"),
                            result.getInt("bestof"),
                            result.getDate("date")
                    );

                    Team away = new Team();
                    away.setName(result.getString("aname"));
                    m.setAwayTeam(away);

                    Team home = new Team();
                    home.setName(result.getString("hname"));
                    m.setHomeTeam(home);

                    return m;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro:" + ex.getMessage());

            throw new SQLException("Erro ao ler match");
        }

        return null;
    }

    @Override
    public void update(Match match) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
            statement.setString(1, match.getUrl());
            statement.setInt(2, match.getHomeId());
            statement.setInt(3, match.getHomeScore());
            statement.setInt(4, match.getAwayId());
            statement.setInt(5, match.getAwayScore());
            statement.setString(6, match.getEventUrl());
            statement.setInt(7, match.getBestOf());
            statement.setDate(8, match.getDate());
            statement.setInt(9, match.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.err.println("Erro:" + ex.getMessage());

            throw new SQLException("Erro ao criar match");
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex.getMessage());

            throw new SQLException("Erro ao deletar match");
        }

    }

    @Override
    public List<Match> all() throws SQLException {
        List<Match> list = new ArrayList<Match>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY)) {
            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    Match m = new Match(
                            result.getInt("id"),
                            result.getString("url"),
                            result.getInt("home_id"),
                            result.getInt("home_score"),
                            result.getInt("away_id"),
                            result.getInt("away_score"),
                            result.getString("event_url"),
                            result.getInt("bestof"),
                            result.getDate("date")
                    );

                    Team away = new Team();
                    away.setName(result.getString("aname"));
                    m.setAwayTeam(away);

                    Team home = new Team();
                    home.setName(result.getString("hname"));
                    m.setHomeTeam(home);

                    list.add(m);
                }

            } catch (SQLException ex) {
                System.err.println("Erro: " + ex.getMessage());

                throw new SQLException("Erro ao selecionar partidas");
            }
        }

        return list;
    }

    public MatchDAO(Connection connection) {
        super(connection);
    }

    @SuppressWarnings("Duplicates")
    public List<Match> matchHistory(int homeId, int awayId) throws SQLException {
        ArrayList<Match> list = new ArrayList<Match>();

        try (PreparedStatement statement = connection.prepareStatement(HISTORY_QUERY)) {
            statement.setInt(1, homeId);
            statement.setInt(2, awayId);
            statement.setInt(3, homeId);
            statement.setInt(4, awayId);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    Match m = new Match(
                            result.getInt("id"),
                            result.getString("url"),
                            result.getInt("home_id"),
                            result.getInt("home_score"),
                            result.getInt("away_id"),
                            result.getInt("away_score"),
                            result.getString("event_url"),
                            result.getInt("bestof"),
                            result.getDate("date")
                    );

                    Team away = new Team();
                    away.setName(result.getString("aname"));
                    m.setAwayTeam(away);

                    Team home = new Team();
                    home.setName(result.getString("hname"));
                    m.setHomeTeam(home);

                    list.add(m);
                }

            } catch (SQLException ex) {
                System.err.println("Erro: " + ex.getMessage());

                throw new SQLException("Erro ao selecionar confrontos");
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao selecionar partida");
        }

        return list;
    }

    public Match lastMatch(int teamid) throws SQLException {
        Match m;
        try (PreparedStatement statement = connection.prepareStatement(LAST_MATCH)) {
            statement.setInt(1, teamid);
            statement.setInt(2, teamid);

            try (ResultSet result = statement.executeQuery()) {

                result.next();
                m = new Match(
                        result.getInt("id"),
                        result.getString("url"),
                        result.getInt("home_id"),
                        result.getInt("home_score"),
                        result.getInt("away_id"),
                        result.getInt("away_score"),
                        result.getString("event_url"),
                        result.getInt("bestof"),
                        result.getDate("date")
                );

                Team away = new Team();
                away.setName(result.getString("aname"));
                m.setAwayTeam(away);

                Team home = new Team();
                home.setName(result.getString("hname"));
                m.setHomeTeam(home);

                return m;

            } catch (SQLException ex) {
                System.err.println("Erro: " + ex.getMessage());

                throw new SQLException("Erro ao selecionar confrontos");
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao selecionar partida");
        }
    }

    public int winCount(int teamid) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(WIN_COUNT)) {
            statement.setInt(1, teamid);
            statement.setInt(2, teamid);

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("cnt");
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao contar partidas");
        }
    }

    public int teamMatchCount(int teamid) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(TEAM_MATCH_COUNT)) {
            statement.setInt(1, teamid);
            statement.setInt(2, teamid);

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("cnt");
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao contar partidas");
        }
    }

    public double winRate(int teamid) throws SQLException {
        int matches = this.teamMatchCount(teamid);
        int wins = this.winCount(teamid);

        return 100.0 * (double) wins / (double) matches;
    }

}
