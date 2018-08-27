<%@ page import="model.Player" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/26/2018
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Players - BD 2018</title>
</head>
<body>
    <h1>Lista de Players</h1>
    <%
        List<Player> list = (List<Player>) request.getAttribute("playerList");

        out.println("<table>");
        out.println("<tr>");
        out.println("<td>Id</td>");
        out.println("<td>Nome</td>");
        out.println("<td>URL</td>");
        out.println("<td>Nacionalidade</td>");
        out.println("</tr>");

        if (list != null) {
            for (Player p : list) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNome() + "</td>");
                out.println("<td><a href=\"" + p.getUrl() + "\">" + p.getUrl() + "</a></td>");
                out.println("<td>" + p.getNationality() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }
    %>
</body>
</html>
