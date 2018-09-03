package json;

import com.google.gson.Gson;
import dao.*;
import model.Match;
import model.Performance;
import model.Player;
import model.Team;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by nick on 9/3/18.
 */
public class ImportJSON {

    class P {
        public String getURL() {
            return URL;
        }

        public int getID() {
            return ID;
        }

        public String getName() {
            return Name;
        }

        public String getNationality() {
            return Nationality;
        }

        public int getKills() {
            return Kills;
        }

        public int getDeaths() {
            return Deaths;
        }

        public double getADR() {
            return ADR;
        }

        public double getKAST() {
            return KAST;
        }

        public double getRating() {
            return Rating;
        }

        String URL;
        int ID;
        String Name;
        String Nationality;
        int Kills;
        int Deaths;
        double ADR;
        double KAST;
        double Rating;

        public P() {

        }
    }

    class T {
        String URL;
        int ID;
        String Name;
        String Nationality;
        P[] Players;

        public String getURL() {
            return URL;
        }

        public int getID() {
            return ID;
        }

        public String getName() {
            return Name;
        }

        public String getNationality() {
            return Nationality;
        }

        public P[] getPlayers() {
            return Players;
        }

        public T() {

        }

    }

    class M {
        int ID;
        String URL;
        T Home;
        int HomeScore;
        T Away;
        int AwayScore;
        String EventURL;
        int BestOf;
        long date;

        public M() {

        }

        public int getID() {
            return ID;
        }

        public String getURL() {
            return URL;
        }

        public T getHome() {
            return Home;
        }

        public int getHomeScore() {
            return HomeScore;
        }

        public T getAway() {
            return Away;
        }

        public int getAwayScore() {
            return AwayScore;
        }

        public String getEventURL() {
            return EventURL;
        }

        public int getBestOf() {
            return BestOf;
        }

        public long getDate() {
            return date;
        }
    }

    Gson gson;

    public ImportJSON() {
        gson = new Gson();
    }

    public void parseAndInsert(String json) {
        M match = gson.fromJson(json, M.class);

        try (DAOFactory daoFactory = new DAOFactory()) {
            MatchDAO matchDAO = daoFactory.getMatchDAO();
            TeamDAO teamDAO = daoFactory.getTeamDAO();
            PlayerDAO playerDAO = daoFactory.getPlayerDAO();
            PerformanceDAO performanceDAO = daoFactory.getPerformanceDAO();

            Team home = new Team(
                    match.getHome().getID(),
                    match.getHome().getName(),
                    match.getHome().getURL(),
                    match.getHome().getNationality()
            );

            Team away = new Team(
                    match.getAway().getID(),
                    match.getAway().getName(),
                    match.getAway().getURL(),
                    match.getAway().getNationality()
            );

            teamDAO.create(home);
            teamDAO.create(away);

            Match m = new Match(
                    match.getID(),
                    match.getURL(),
                    match.getHome().getID(),
                    match.getHomeScore(),
                    match.getAway().getID(),
                    match.getAwayScore(),
                    match.getEventURL(),
                    match.getBestOf(),
                    new Date(match.getDate())
            );

            matchDAO.create(m);

            P[] homePlayers = match.getHome().getPlayers();
            P[] awayPlayers = match.getAway().getPlayers();

            exportPerformances(playerDAO, performanceDAO, home, m, homePlayers);

            exportPerformances(playerDAO, performanceDAO, away, m, awayPlayers);


        } catch (SQLException | IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Aconteceu algum erro fazendo o JSON");
        }


    }

    private void exportPerformances(PlayerDAO playerDAO, PerformanceDAO performanceDAO, Team away, Match m, P[] awayPlayers) throws SQLException {
        for (P p : awayPlayers) {
            Player player = new Player(
                    p.getID(),
                    p.getName(),
                    p.getURL(),
                    p.getNationality()
            );

            Performance performance = new Performance(p.getID(),
                    away.getId(),
                    m.getId(),
                    p.getKills(),
                    p.getDeaths(),
                    p.getADR(),
                    p.getKAST(),
                    p.getRating()
            );

            playerDAO.create(player);
            performanceDAO.create(performance);
        }
    }

}
