package controller;

import dao.*;
import model.Match;
import model.Performance;
import model.Player;
import model.Team;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PerformanceServlet", urlPatterns = {
        "/performance",
        "/performance/create",
        "/performance/read",
        "/performance/update",
        "/performance/delete"
})
public class PerformanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId;
        int teamId;
        int matchId;
        int kills;
        int deaths;
        double adr;
        double kast;
        double rating;

        switch (request.getServletPath()) {
            case "/player/create":
                playerId = Integer.parseInt(request.getParameter("playerid"));
                teamId = Integer.parseInt(request.getParameter("teamid"));
                matchId = Integer.parseInt(request.getParameter("matchid"));
                kills = Integer.parseInt(request.getParameter("kills"));
                deaths = Integer.parseInt(request.getParameter("deaths"));
                adr = Double.parseDouble(request.getParameter("adr"));
                kast = Double.parseDouble(request.getParameter("kast"));
                rating = Double.parseDouble(request.getParameter("rating"));

                try (DAOFactory daoFactory = new DAOFactory()) {
                    PerformanceDAO dao = daoFactory.getPerformanceDAO();

                    dao.create(new Performance(playerId, teamId, matchId, kills, deaths, adr, kast, rating));

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/performance");

                break;

            case "/player/update":
                playerId = Integer.parseInt(request.getParameter("playerid"));
                teamId = Integer.parseInt(request.getParameter("teamid"));
                matchId = Integer.parseInt(request.getParameter("matchid"));
                kills = Integer.parseInt(request.getParameter("kills"));
                deaths = Integer.parseInt(request.getParameter("deaths"));
                adr = Double.parseDouble(request.getParameter("adr"));
                kast = Double.parseDouble(request.getParameter("kast"));
                rating = Double.parseDouble(request.getParameter("rating"));

                try (DAOFactory daoFactory = new DAOFactory()) {
                    PerformanceDAO dao = daoFactory.getPerformanceDAO();

                    dao.update(new Performance(playerId, teamId, matchId, kills, deaths, adr, kast, rating));

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/performance");

                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int playerId;
        int teamId;
        int matchId;

        switch (request.getServletPath()) {
            case "/performance":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    PerformanceDAO dao =  daoFactory.getPerformanceDAO();

                    List<Performance> list = dao.all();

                    request.setAttribute("performanceList", list);

                    dispatcher = request.getRequestDispatcher("/performance/List.jsp");
                    dispatcher.forward(request,response);
                } catch (ClassNotFoundException | SQLException | IOException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());

                }


                break;

            case "/performance/delete":

                playerId = Integer.parseInt(request.getParameter("playerid"));
                teamId = Integer.parseInt(request.getParameter("teamid"));
                matchId = Integer.parseInt(request.getParameter("matchid"));

                try (DAOFactory daoFactory = new DAOFactory()) {
                    PerformanceDAO dao = daoFactory.getPerformanceDAO();

                    dao.delete(playerId, teamId, matchId);

                    response.sendRedirect(request.getContextPath() + "/performance");

                } catch (ClassNotFoundException | SQLException | IOException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                }

                break;

            case "/performance/create":

                try (DAOFactory daoFactory = new DAOFactory()) {
                    PlayerDAO playerDAO = daoFactory.getPlayerDAO();
                    MatchDAO matchDAO = daoFactory.getMatchDAO();
                    TeamDAO teamDAO = daoFactory.getTeamDAO();

                    List<Player> playerList = playerDAO.all();
                    List<Match> matchList = matchDAO.all();
                    List<Team> teamList = teamDAO.all();

                    request.setAttribute("playerList", playerList);
                    request.setAttribute("matchList", matchList);
                    request.setAttribute("teamList", teamList);

                    dispatcher = request.getRequestDispatcher("/performance/Cadastro.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                }
                break;

            case "/performance/update":
                playerId = Integer.parseInt(request.getParameter("playerid"));
                teamId = Integer.parseInt(request.getParameter("teamid"));
                matchId = Integer.parseInt(request.getParameter("matchid"));

                try (DAOFactory daoFactory = new DAOFactory()) {
                    PlayerDAO playerDAO = daoFactory.getPlayerDAO();
                    MatchDAO matchDAO = daoFactory.getMatchDAO();
                    TeamDAO teamDAO = daoFactory.getTeamDAO();
                    PerformanceDAO dao = daoFactory.getPerformanceDAO();

                    List<Player> playerList = playerDAO.all();
                    List<Match> matchList = matchDAO.all();
                    List<Team> teamList = teamDAO.all();
                    Performance performance = dao.read(playerId, teamId, matchId);

                    request.setAttribute("performance", performance);

                    dispatcher = request.getRequestDispatcher("/performance/Update.jsp");
                    dispatcher.forward(request, response);

                } catch (ClassNotFoundException | SQLException | IOException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                }

                break;
        }

    }
}
