package controller;

import dao.DAOFactory;
import dao.MatchDAO;
import dao.TeamDAO;
import json.ImportJSON;
import model.Match;
import model.Team;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
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
@MultipartConfig
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

                Part part = request.getPart("json");

                if (part != null) {
                    InputStream is = part.getInputStream();
                    BufferedReader fileStream = new BufferedReader(new InputStreamReader(is));

                    ImportJSON importJSON = new ImportJSON();

                    while (fileStream.ready()) {
                        String line = fileStream.readLine();
                        importJSON.parseAndInsert(line);
                    }

                    fileStream.close();
                    part.delete();

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
                break;

            case "/match/json":
                /*
                ImportJSON importJSON = new ImportJSON();

                importJSON.parseAndInsert("{\"ID\":2325755,\"URL\":\"https://www.hltv.org/matches/2325755/mousesports-vs-mibr-dreamhack-masters-stockholm-2018\",\"Home\":{\"ID\":4494,\"Name\":\"mousesports\",\"URL\":\"https://hltv.org/team/4494/mousesports\",\"Nationality\":\"Europe\",\"Players\":[{\"URL\":\"https://hltv.org/player/5479/suNny\",\"ID\":5479,\"Name\":\"suNny\",\"Nationality\":\"Finland\",\"Kills\":29,\"Deaths\":33,\"ADR\":86.4,\"KAST\":65.2,\"Rating\":1},{\"URL\":\"https://hltv.org/player/798/oskar\",\"ID\":798,\"Name\":\"oskar\",\"Nationality\":\"Czech Republic\",\"Kills\":29,\"Deaths\":34,\"ADR\":57.6,\"KAST\":56.5,\"Rating\":0.87},{\"URL\":\"https://hltv.org/player/11816/ropz\",\"ID\":11816,\"Name\":\"ropz\",\"Nationality\":\"Estonia\",\"Kills\":25,\"Deaths\":34,\"ADR\":55.5,\"KAST\":63,\"Rating\":0.79},{\"URL\":\"https://hltv.org/player/2730/chrisJ\",\"ID\":2730,\"Name\":\"chrisJ\",\"Nationality\":\"Netherlands\",\"Kills\":21,\"Deaths\":38,\"ADR\":57.7,\"KAST\":65.2,\"Rating\":0.76},{\"URL\":\"https://hltv.org/player/2553/Snax\",\"ID\":2553,\"Name\":\"Snax\",\"Nationality\":\"Poland\",\"Kills\":15,\"Deaths\":34,\"ADR\":36.8,\"KAST\":54.3,\"Rating\":0.55}]},\"HomeScore\":0,\"Away\":{\"ID\":9215,\"Name\":\"MIBR\",\"URL\":\"https://hltv.org/team/9215/mibr\",\"Nationality\":\"Brazil\",\"Players\":[{\"URL\":\"https://hltv.org/player/8523/tarik\",\"ID\":8523,\"Name\":\"tarik\",\"Nationality\":\"United States\",\"Kills\":46,\"Deaths\":22,\"ADR\":97.1,\"KAST\":80.4,\"Rating\":1.6},{\"URL\":\"https://hltv.org/player/9216/coldzera\",\"ID\":9216,\"Name\":\"coldzera\",\"Nationality\":\"Brazil\",\"Kills\":47,\"Deaths\":18,\"ADR\":92.7,\"KAST\":84.8,\"Rating\":1.58},{\"URL\":\"https://hltv.org/player/8564/fer\",\"ID\":8564,\"Name\":\"fer\",\"Nationality\":\"Brazil\",\"Kills\":26,\"Deaths\":27,\"ADR\":72.7,\"KAST\":78.3,\"Rating\":1.13},{\"URL\":\"https://hltv.org/player/8797/Stewie2K\",\"ID\":8797,\"Name\":\"Stewie2K\",\"Nationality\":\"United States\",\"Kills\":31,\"Deaths\":28,\"ADR\":71.3,\"KAST\":76.1,\"Rating\":1.11},{\"URL\":\"https://hltv.org/player/2023/FalleN\",\"ID\":2023,\"Name\":\"FalleN\",\"Nationality\":\"Brazil\",\"Kills\":23,\"Deaths\":26,\"ADR\":61.8,\"KAST\":82.6,\"Rating\":0.99}]},\"AwayScore\":2,\"EventURL\":\"https://hltv.com/events/3389/dreamhack-masters-stockholm-2018\",\"BestOf\":3,\"Date\":1535649000000}");
                */
                dispatcher = request.getRequestDispatcher("/match/json.jsp");
                dispatcher.forward(request, response);
        }
    }
}
