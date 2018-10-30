package controller;

import dao.DAOFactory;
import dao.StatsDAO;
import model.Stats;

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

@WebServlet(name = "StatsController",
    urlPatterns = {
        "/stats"
    })
public class StatsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/stats":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    StatsDAO dao = daoFactory.getStatsDAO();

                    List<Stats> nations = dao.nations();

                    request.setAttribute("nations", nations);

                    RequestDispatcher view = request.getRequestDispatcher("/stats/Stats.jsp");
                    view.forward(request, response);

                } catch (IOException | SQLException | ClassNotFoundException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                    request.getSession().setAttribute("error", ex.getMessage());
                }
                break;
        }
    }
}
