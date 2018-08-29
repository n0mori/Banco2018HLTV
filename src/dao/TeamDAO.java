package dao;

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
public class TeamDAO extends DAO<Team> {

    private static final String CREATE_QUERY
            = "INSERT INTO hltv.team "
            + "(id, \"name\", url, nationality)"
            + "VALUES (?,?,?,?) RETURNING id;";

    private static final String READ_QUERY
            = "SELECT id, \"name\", url, nationality "
            + "FROM hltv.team "
            + "WHERE id = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE hltv.team "
            + "SET name = ?, url = ?, nationality = ? "
            + "WHERE id = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM hltv.team "
            + "WHERE id = ?;";

    private static final String ALL_QUERY
            = "SELECT id, \"name\", url, nationality "
            + "FROM hltv.team "
            + "ORDER BY \"name\";";

    public TeamDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Team team) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
            statement.setInt(1, team.getId());
            statement.setString(2, team.getName());
            statement.setString(3, team.getUrl());
            statement.setString(4, team.getNationality());

            statement.execute();
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao criar team");
        }
    }

    @Override
    public Team read(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY);) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    return new Team(
                            result.getInt("id"),
                            result.getString("nome"),
                            result.getString("url"),
                            result.getString("nationality")
                    );
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao fazer ler team");
        }
        return null;
    }

    @Override
    public void update(Team team) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
            statement.setString(1, team.getName());
            statement.setString(2, team.getUrl());
            statement.setString(3, team.getNationality());
            statement.setInt(4, team.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro ao atualizar team");
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
            statement.setInt(1, id);

            statement.executeQuery();

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }

    }

    @Override
    public List<Team> all() throws SQLException {
        List<Team> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Team team = new Team(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("url"),
                        resultSet.getString("nationality")
                );

                list.add(team);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return list;
    }
}
