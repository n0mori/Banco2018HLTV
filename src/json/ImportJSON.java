package json;

import com.google.gson.Gson;
import model.Player;

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
            return kills;
        }

        public int getDeaths() {
            return deaths;
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
        int kills;
        int deaths;
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

        System.err.println(match.getAway().getPlayers()[1].getName());
    }

}
