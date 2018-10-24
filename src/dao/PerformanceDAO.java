package dao;

import model.Performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 9/3/18.
 */
public class PerformanceDAO extends DAO<Performance> {

    private static final String CREATE_QUERY
            = "INSERT INTO hltv.performance"
            + "(playerid, teamid, matchid, kills, deaths, adr, kast, rating) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
            + "ON CONFLICT ON CONSTRAINT pk_performance DO UPDATE SET "
            + "kills = ?, deaths = ?, adr = ?, kast = ?, rating = ? "
            + "WHERE hltv.performance.playerid = ? AND hltv.performance.teamid = ? AND hltv.performance.matchid = ?;";

    private static final String READ_QUERY
            = "SELECT playerid, teamid, matchid, kills, deaths, adr, kast, rating "
            + "FROM hltv.performance "
            + "WHERE playerid = ? AND teamid = ? AND matchid = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE hltv.performance "
            + "SET kills = ?, deaths = ?, adr = ?, kast = ?, rating = ? "
            + "WHERE playerid = ? AND teamid = ? AND matchid = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM hltv.performance "
            + "WHERE playerid = ? AND teamid = ? AND matchid = ?;";

    private static final String ALL_QUERY
            = "SELECT playerid, teamid, matchid, kills, deaths, adr, kast, rating "
            + "FROM hltv.performance "
            + "ORDER BY matchid DESC, teamid ASC, rating DESC;";

    private static final String MATCH_QUERY
            = "SELECT playerid, teamid, matchid, kills, deaths, adr, kast, rating "
            + "FROM hltv.Performance "
            + "WHERE matchid = ? "
            + "ORDER BY teamid, rating;";

    public PerformanceDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Performance performance) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, performance.getPlayerId());
            statement.setInt(2, performance.getTeamId());
            statement.setInt(3, performance.getMatchId());
            statement.setInt(4, performance.getKills());
            statement.setInt(5, performance.getDeaths());
            statement.setDouble(6, performance.getAdr());
            statement.setDouble(7, performance.getKast());
            statement.setDouble(8, performance.getRating());
            statement.setInt(9, performance.getKills());
            statement.setInt(10, performance.getDeaths());
            statement.setDouble(11, performance.getAdr());
            statement.setDouble(12, performance.getKast());
            statement.setDouble(13, performance.getRating());
            statement.setInt(14, performance.getPlayerId());
            statement.setInt(15, performance.getTeamId());
            statement.setInt(16, performance.getMatchId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro:" + ex.getMessage());

            throw new SQLException("Erro ao criar performance");
        }

    }

    @Override
    public Performance read(Integer id) throws SQLException {
        return null;
    }

    public Performance read(Integer playerId, Integer teamId, Integer matchId ) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, playerId);
            statement.setInt(2, teamId);
            statement.setInt(3, matchId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Performance(
                            resultSet.getInt("playerid"),
                            resultSet.getInt("teamid"),
                            resultSet.getInt("matchid"),
                            resultSet.getInt("kills"),
                            resultSet.getInt("deaths"),
                            resultSet.getDouble("adr"),
                            resultSet.getDouble("kast"),
                            resultSet.getDouble("rating")
                    );
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao ler performance");
        }

        return null;
    }

    @Override
    public void update(Performance performance) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, performance.getKills());
            statement.setInt(2, performance.getDeaths());
            statement.setDouble(3, performance.getAdr());
            statement.setDouble(4, performance.getKast());
            statement.setDouble(5, performance.getRating());
            statement.setInt(6, performance.getPlayerId());
            statement.setInt(7, performance.getTeamId());
            statement.setInt(8, performance.getMatchId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao atualizar performance");
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    public void delete(Integer playerId, Integer teamId, Integer matchId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, playerId);
            statement.setInt(2, teamId);
            statement.setInt(3, matchId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao deletar performance");
        }

    }

    @Override
    public List<Performance> all() throws SQLException {
        ArrayList<Performance> list = new ArrayList<Performance>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                     list.add(new Performance(
                            resultSet.getInt("playerid"),
                            resultSet.getInt("teamid"),
                            resultSet.getInt("matchid"),
                            resultSet.getInt("kills"),
                            resultSet.getInt("deaths"),
                            resultSet.getDouble("adr"),
                            resultSet.getDouble("kast"),
                            resultSet.getDouble("rating")
                    ));
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao ler performance");
        }
        return list;
    }

    public List<Performance> matchPerformance(int id) throws SQLException {
        ArrayList<Performance> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(MATCH_QUERY)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new Performance(
                            resultSet.getInt("playerid"),
                            resultSet.getInt("teamid"),
                            resultSet.getInt("matchid"),
                            resultSet.getInt("kills"),
                            resultSet.getInt("deaths"),
                            resultSet.getDouble("adr"),
                            resultSet.getDouble("kast"),
                            resultSet.getDouble("rating")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao ler performances");
        }

        return list;
    }
}
