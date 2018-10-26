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
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Lista de Players</h1>

    <a href="/player/create">Create</a>
    <a href="/player/rankings">Ranking</a>
    <%
        List<Player> list = (List<Player>) request.getAttribute("playerList");

        out.println("<table class=\"table\">");
        out.println("<thead class=\"thead-dark\">");
        out.println("<tr>");
        out.println("<td scope=\"col\">Id</td>");
        out.println("<td scope=\"col\">Nome</td>");
        out.println("<td scope=\"col\">URL</td>");
        out.println("<td scope=\"col\">Nacionalidade</td>");
        out.println("<td scope=\"col\"></td>");
        out.println("<td scope=\"col\"></td>");
        out.println("</tr>");
        out.println("</thead>");

        if (list != null) {
            for (Player p : list) {
                out.println("<tr>");
                out.println("<td><a href=\"/player/details?id=" + p.getId() + "\">" + p.getId() + "</a></td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td><a href=\"" + p.getUrl() + "\">" + p.getUrl() + "</a></td>");
                out.println("<td>" + p.getNationality() + "</td>");
                out.println("<td><a href=\"/player/update?id=" + p.getId() + "\">Edit</a></td>");
                out.println("<td><a href=\"/player/delete?id=" + p.getId() + "\">Remove</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }
    %>
</div>
</body>
</html>
