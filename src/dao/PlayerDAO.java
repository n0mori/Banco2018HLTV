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
            + "(id, nome, url, nationality)"
            + "VALUES (?,?,?,?) RETURNING id";

    private static final String READ_QUERY
            = "SELECT login, nome, nascimento, avatar "
            + "FROM j2ee.usuario "
            + "WHERE id = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE j2ee.usuario "
            + "SET login = ?, nome = ?, nascimento = ? "
            + "WHERE id = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM j2ee.usuario "
            + "WHERE id = ?;";

    private static final String ALL_QUERY
            = "SELECT id, nome, url, nationality "
            + "FROM hltv.player "
            + "ORDER BY nome;";

    public PlayerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Player player) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
            statement.setInt(1, player.getId());
            statement.setString(2, player.getNome());
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
        return null;
    }

    @Override
    public void update(Player player) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public List<Player> all() throws SQLException {
        List<Player> playerList = new ArrayList<Player>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Player player = new Player(
                        result.getInt("id"),
                        result.getString("nome"),
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
