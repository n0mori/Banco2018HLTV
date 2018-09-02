package controller;

import dao.DAOFactory;
import dao.MatchDAO;
import dao.TeamDAO;
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
        "/match/delete"
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
        }
    }
}
