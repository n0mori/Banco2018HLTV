package controller;

import dao.*;
import model.Match;
import model.Player;
import model.Team;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeamController", urlPatterns = {
        "/team",
        "/team/create",
        "/team/read",
        "/team/update",
        "/team/delete",
        "/team/details"
})
public class TeamController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int id;
        String nome;
        String url;
        String nationality;
        Team t;

        switch (request.getServletPath()) {
            case "/team/create":
                id = Integer.parseInt(request.getParameter("id"));
                nome = request.getParameter("name");
                url = request.getParameter("url");
                nationality = request.getParameter("nationality");

                t = new Team(id, nome, url, nationality);

                try (DAOFactory daoFactory = new DAOFactory();) {
                    TeamDAO dao = daoFactory.getTeamDAO();

                    dao.create(t);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/team");

                break;
            case "/team/update":
                id = Integer.parseInt(request.getParameter("id"));
                nome = request.getParameter("name");
                url = request.getParameter("url");
                nationality = request.getParameter("nationality");

                t = new Team(id, nome, url, nationality);

                try (DAOFactory daoFactory = new DAOFactory();) {
                    TeamDAO dao = daoFactory.getTeamDAO();

                    dao.update(t);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/team");
                break;

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/team":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    TeamDAO dao = daoFactory.getTeamDAO();

                    List<Team> list = dao.all();

                    request.setAttribute("teamList", list);

                    dispatcher = request.getRequestDispatcher("team/List.jsp");
                    dispatcher.forward(request, response);


                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                break;
            case "/team/create":
                dispatcher = request.getRequestDispatcher("/team/Cadastro.jsp");
                dispatcher.forward(request, response);
                break;
            case "/team/update":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    TeamDAO dao = daoFactory.getTeamDAO();

                    Team team = dao.read(Integer.parseInt(request.getParameter("id")));

                    request.setAttribute("team", team);

                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/team/Update.jsp");
                dispatcher.forward(request, response);
                break;
            case "/team/delete":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    TeamDAO dao = daoFactory.getTeamDAO();

                    dao.delete(Integer.parseInt(request.getParameter("id")));

                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/team");
                break;

            case "/team/details":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    TeamDAO teamDAO = daoFactory.getTeamDAO();
                    MatchDAO matchDAO = daoFactory.getMatchDAO();
                    PerformanceDAO performanceDAO = daoFactory.getPerformanceDAO();
                    PlayerDAO playerDAO = daoFactory.getPlayerDAO();

                    int teamid = Integer.parseInt(request.getParameter("id"));

                    Team team = teamDAO.read(teamid);
                    Match match = matchDAO.lastMatch(teamid);
                    double winRate = matchDAO.winRate(teamid);

                    List<Integer> playerids = performanceDAO.lastLineup(teamid);
                    List<Player> players = new ArrayList<>();

                    for (int id : playerids) {
                        players.add(playerDAO.read(id));
                    }

                    request.setAttribute("team", team);
                    request.setAttribute("match", match);
                    request.setAttribute("winRate", winRate);
                    request.setAttribute("players", players);

                    dispatcher = request.getRequestDispatcher("/team/Details.jsp");
                    dispatcher.forward(request, response);

                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                break;
        }

    }
}
