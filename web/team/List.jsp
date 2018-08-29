<%@ page import="model.Player" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Team" %><%--
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

    <a href="/team/create">Create</a>

    <table class=\"table\">
    <thead class=\"thead-dark\">
    <tr>
    <td scope=\"col\">Id</td>
    <td scope=\"col\">Nome</td>
    <td scope=\"col\">URL</td>
    <td scope=\"col\">Nacionalidade</td>
    <td scope=\"col\"></td>
    <td scope=\"col\"></td>
    </tr>
    </thead>

    <%
        List<Team> list = (List<Team>) request.getAttribute("playerList");


        if (list != null) {
            for (Team t : list) {
                out.println("<tr>");
                out.println("<td>" + t.getId() + "</td>");
                out.println("<td>" + t.getName() + "</td>");
                out.println("<td><a href=\"" + t.getUrl() + "\">" + t.getUrl() + "</a></td>");
                out.println("<td>" + t.getNationality() + "</td>");
                out.println("<td><a href=\"/player/update?id=" + t.getId() + "\">Edit</a></td>");
                out.println("<td><a href=\"/player/delete?id=" + t.getId() + "\">Remove</a></td>");
                out.println("</tr>");
            }

        }
    %>

    </table>
</div>
</body>
</html>
