package dao;

import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO extends DAO<Player> {

    private static final String CREATE_QUERY
            = "INSERT INTO hltv.player"
            + "(id, \"name\", url, nationality)"
            + "VALUES (?,?,?,?) RETURNING id;";

    private static final String READ_QUERY
            = "SELECT id, \"name\", url, nationality "
            + "FROM hltv.player "
            + "WHERE id = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE hltv.player "
            + "SET \"name\" = ?, url = ?, nationality = ? "
            + "WHERE id = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM hltv.player "
            + "WHERE id = ?;";

    private static final String ALL_QUERY
            = "SELECT id, \"name\", url, nationality "
            + "FROM hltv.player "
            + "ORDER BY \"name\";";

    public PlayerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Player player) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
            statement.setInt(1, player.getId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getUrl());
            statement.setString(4, player.getNationality());

            statement.executeQuery();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao criar player");
        }
    }

    @Override
    public Player read(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY);) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    return new Player(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("url"),
                            resultSet.getString("nationality")
                    );
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao encontrar player");
        }
        return null;
    }

    @Override
    public void update(Player player) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
            statement.setString(1, player.getName());
            statement.setString(2, player.getUrl());
            statement.setString(3, player.getNationality());
            statement.setInt(4, player.getId());

            statement.executeQuery();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao fazer update player");
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
            statement.setInt(1, id);

            statement.executeQuery();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao deletar player");
        }

    }

    @Override
    public List<Player> all() throws SQLException {
        List<Player> playerList = new ArrayList<Player>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Player player = new Player(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("url"),
                        result.getString("nationality")
                );
                playerList.add(player);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new SQLException("Erro ao listar players");
        }

        return playerList;
    }
}
