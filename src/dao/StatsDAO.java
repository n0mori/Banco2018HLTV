package dao;

import model.Stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsDAO extends DAO<Stats> {

    public static final String NATIONALITY_QUERY
            = "SELECT COUNT(*) as cnt, nationality "
            + "FROM hltv.team "
            + "GROUP BY nationality "
            + "ORDER BY cnt DESC;";

    public StatsDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Stats stats) throws SQLException {

    }

    @Override
    public Stats read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(Stats stats) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public List<Stats> all() throws SQLException {
        return null;
    }

    public List<Stats> nations() throws SQLException {
        ArrayList<Stats> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(NATIONALITY_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new Stats(resultSet.getString("nationality"), resultSet.getInt("cnt")));
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());

            throw new SQLException("Erro nos stats de paises");
        }

        return list;

    }

}
