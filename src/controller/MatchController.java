package controller;

import dao.DAOFactory;
import dao.MatchDAO;
import dao.TeamDAO;
import json.ImportJSON;
import model.Match;
import model.Team;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MatchController", urlPatterns = {
        "/match",
        "/match/create",
        "/match/read",
        "/match/update",
        "/match/delete",
        "/match/json"
})
public class MatchController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String url;
        int homeId;
        int homeScore;
        int awayId;
        int awayScore;
        String eventUrl;
        int bestOf;
        Date date;
        Match match;

        switch(request.getServletPath()) {
            case "/match/create":
                id = Integer.parseInt(request.getParameter("id"));
                url = request.getParameter("url");
                homeId = Integer.parseInt(request.getParameter("homeId"));
                homeScore = Integer.parseInt(request.getParameter("homeScore"));
                awayId = Integer.parseInt(request.getParameter("awayId"));
                awayScore = Integer.parseInt(request.getParameter("awayScore"));
                eventUrl = request.getParameter("eventUrl");
                bestOf = Integer.parseInt(request.getParameter("bestOf"));
                date = Date.valueOf(request.getParameter("date"));

                match = new Match(id, url, homeId, homeScore, awayId, awayScore, eventUrl, bestOf, date);

                try (DAOFactory daoFactory = new DAOFactory();) {
                    MatchDAO dao = daoFactory.getMatchDAO();

                    dao.create(match);

                } catch (IOException | SQLException | ClassNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/match");

                break;

            case "/match/update":
                id = Integer.parseInt(request.getParameter("id"));
                url = request.getParameter("url");
                homeId = Integer.parseInt(request.getParameter("homeId"));
                homeScore = Integer.parseInt(request.getParameter("homeScore"));
                awayId = Integer.parseInt(request.getParameter("awayId"));
                awayScore = Integer.parseInt(request.getParameter("awayScore"));
                eventUrl = request.getParameter("eventUrl");
                bestOf = Integer.parseInt(request.getParameter("bestOf"));
                date = Date.valueOf(request.getParameter("date"));

                match = new Match(id, url, homeId, homeScore, awayId, awayScore, eventUrl, bestOf, date);

                try (DAOFactory daoFactory = new DAOFactory();) {
                    MatchDAO dao = daoFactory.getMatchDAO();

                    dao.update(match);

                } catch (IOException | SQLException | ClassNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/match");
                break;

            case "/match/json":

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/match":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    MatchDAO dao = daoFactory.getMatchDAO();

                    List<Match> matchList = dao.all();

                    request.setAttribute("matchList", matchList);

                    dispatcher = request.getRequestDispatcher("/match/List.jsp");
                    dispatcher.forward(request, response);

                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                }
                break;

            case "/match/create":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    TeamDAO dao = daoFactory.getTeamDAO();

                    List<Team> teamList = dao.all();

                    request.setAttribute("teamList", teamList);

                    dispatcher = request.getRequestDispatcher("/match/Cadastro.jsp");
                    dispatcher.forward(request, response);

                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                }
                break;

            case "/match/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    TeamDAO teamDAO = daoFactory.getTeamDAO();
                    MatchDAO matchDAO = daoFactory.getMatchDAO();

                    List<Team> teamList = teamDAO.all();
                    Match match = matchDAO.read(Integer.parseInt(request.getParameter("id")));

                    request.setAttribute("teamList", teamList);
                    request.setAttribute("match", match);

                    dispatcher = request.getRequestDispatcher("/match/Update.jsp");
                    dispatcher.forward(request, response);

                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                }
                break;

            case "/match/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    MatchDAO dao = daoFactory.getMatchDAO();

                    dao.delete(Integer.parseInt(request.getParameter("id")));

                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/match");
                break;

            case "/match/json":
                /*
                ImportJSON importJSON = new ImportJSON();

                importJSON.parseAndInsert("{\"ID\":2317926,\"URL\":\"https://www.hltv.org/matches/2317926/faze-vs-sk-esl-pro-league-season-6-finals\",\"Home\":{\"ID\":6667,\"Name\":\"FaZe\",\"URL\":\"https://hltv.org/team/6667/faze\",\"Nationality\":\"Europe\",\"Players\":[{\"URL\":\"https://hltv.org/player/8183/rain\",\"ID\":8183,\"Name\":\"rain\",\"Nationality\":\"Norway\",\"Kills\":84,\"Deaths\":79,\"ADR\":82.5,\"KAST\":71.6,\"Rating\":1.17},{\"URL\":\"https://hltv.org/player/2757/GuardiaN\",\"ID\":2757,\"Name\":\"GuardiaN\",\"Nationality\":\"Slovakia\",\"Kills\":82,\"Deaths\":70,\"ADR\":79.8,\"KAST\":75.9,\"Rating\":1.16},{\"URL\":\"https://hltv.org/player/885/olofmeister\",\"ID\":885,\"Name\":\"olofmeister\",\"Nationality\":\"Sweden\",\"Kills\":75,\"Deaths\":78,\"ADR\":70,\"KAST\":65.5,\"Rating\":1},{\"URL\":\"https://hltv.org/player/3741/NiKo\",\"ID\":3741,\"Name\":\"NiKo\",\"Nationality\":\"Bosnia and Herzegovina\",\"Kills\":76,\"Deaths\":82,\"ADR\":77.4,\"KAST\":66.4,\"Rating\":0.97},{\"URL\":\"https://hltv.org/player/429/karrigan\",\"ID\":429,\"Name\":\"karrigan\",\"Nationality\":\"Denmark\",\"Kills\":62,\"Deaths\":76,\"ADR\":56.3,\"KAST\":70.7,\"Rating\":0.89}]},\"HomeScore\":1,\"Away\":{\"ID\":6137,\"Name\":\"SK\",\"URL\":\"https://hltv.org/team/6137/sk\",\"Nationality\":\"Brazil\",\"Players\":[{\"URL\":\"https://hltv.org/player/9216/coldzera\",\"ID\":9216,\"Name\":\"coldzera\",\"Nationality\":\"Brazil\",\"Kills\":78,\"Deaths\":67,\"ADR\":73.4,\"KAST\":70.7,\"Rating\":1.1},{\"URL\":\"https://hltv.org/player/8564/fer\",\"ID\":8564,\"Name\":\"fer\",\"Nationality\":\"Brazil\",\"Kills\":88,\"Deaths\":85,\"ADR\":78.9,\"KAST\":66.4,\"Rating\":1.08},{\"URL\":\"https://hltv.org/player/2023/FalleN\",\"ID\":2023,\"Name\":\"FalleN\",\"Nationality\":\"Brazil\",\"Kills\":79,\"Deaths\":74,\"ADR\":75.2,\"KAST\":70.7,\"Rating\":1.08},{\"URL\":\"https://hltv.org/player/9217/TACO\",\"ID\":9217,\"Name\":\"TACO\",\"Nationality\":\"Brazil\",\"Kills\":70,\"Deaths\":79,\"ADR\":64.1,\"KAST\":63.8,\"Rating\":0.96},{\"URL\":\"https://hltv.org/player/8568/boltz\",\"ID\":8568,\"Name\":\"boltz\",\"Nationality\":\"Brazil\",\"Kills\":70,\"Deaths\":75,\"ADR\":67.7,\"KAST\":63.8,\"Rating\":0.92}]},\"AwayScore\":3,\"EventURL\":\"https://hltv.com/events/3072/esl-pro-league-season-6-finals\",\"BestOf\":5,\"Date\":1512918000000}");
                */
                dispatcher = request.getRequestDispatcher("/match/json.jsp");
                dispatcher.forward(request, response);
        }
    }
}
