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
import java.awt.desktop.SystemEventListener;
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
        int id;
        String nome;
        String url;
        String nationality;
        Player p;

        switch (request.getServletPath()) {
            case "/player/create":
                id = Integer.parseInt(request.getParameter("id"));
                nome = request.getParameter("name");
                url = request.getParameter("url");
                nationality = request.getParameter("nationality");

                p = new Player(id, nome, url, nationality);

                try (DAOFactory daoFactory = new DAOFactory();) {
                    PlayerDAO dao = daoFactory.getPlayerDAO();

                    dao.create(p);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/player");

                break;

            case "/player/update":
                id = Integer.parseInt(request.getParameter("id"));
                nome = request.getParameter("name");
                url = request.getParameter("url");
                nationality = request.getParameter("nationality");

                p = new Player(id, nome, url, nationality);

                try (DAOFactory daoFactory = new DAOFactory();) {
                    PlayerDAO dao = daoFactory.getPlayerDAO();

                    dao.update(p);

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
            case "/player/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getPlayerDAO();

                    Player player = dao.read(Integer.parseInt(request.getParameter("id")));

                    request.setAttribute("player", player);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/playerUpdate.jsp");
                dispatcher.forward(request, response);
                break;

            case "/player/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getPlayerDAO();

                    dao.delete(Integer.parseInt(request.getParameter("id")));

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }
                response.sendRedirect(request.getContextPath() + "/player");
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
