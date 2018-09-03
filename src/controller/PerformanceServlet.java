package controller;

import dao.DAOFactory;
import dao.PerformanceDAO;
import model.Performance;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


        }

    }
}
