package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.PlayerDAO;
import model.Player;

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

@WebServlet(name = "PlayerController", urlPatterns = {
        "/player/create",
        "/player/read",
        "/player/update",
        "/player/delete",
        "/player"
})
public class PlayerController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/player/create":
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String url = request.getParameter("url");
                String nationality = request.getParameter("nationality");

                Player p = new Player(id, nome, url, nationality);

                try (DAOFactory daoFactory = new DAOFactory();) {
                    PlayerDAO dao = daoFactory.getPlayerDAO();

                    dao.create(p);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/player");

                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
        DAO<Player> dao;

        switch (request.getServletPath()) {
            case "/player/create":
                dispatcher = request.getRequestDispatcher("/playerCadastro.jsp");
                dispatcher.forward(request, response);
                break;
            case "/player":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getPlayerDAO();

                    List<Player> list = dao.all();

                    if (list != null) {
                        request.setAttribute("playerList", list);
                    }

                    dispatcher = request.getRequestDispatcher("/playerList.jsp");
                    dispatcher.forward(request, response);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println(ex.getMessage());
                    request.getSession().setAttribute("error", ex.getMessage());
                }


                break;
        }
    }
}
