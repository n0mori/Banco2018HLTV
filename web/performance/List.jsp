<%@ page import="model.Player" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Team" %>
<%@ page import="model.Performance" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/26/2018
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teams - BD 2018</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Lista de Performances</h1>

    <a href="/performance/create">Create</a>

    <table class="table">
    <thead class="thead-dark">
    <tr>
    <td scope="col">Match</td>
    <td scope="col">Team</td>
    <td scope="col">Player</td>
    <td scope="col">K-D</td>
    <td scope="col">ADR</td>
    <td scope="col">KAST</td>
    <td scope="col">Rating</td>
    <td scope="col"></td>
    <td scope="col"></td>
    </tr>
    </thead>

    <%
        List<Performance> list = (List<Performance>) request.getAttribute("performanceList");


        if (list != null) {
            for (Performance p : list) {
                out.println("<tr>");
                out.println("<td>" + p.getMatchId() + "</td>");
                out.println("<td>" + p.getTeamId() + "</td>");
                out.println("<td>" + p.getPlayerId() + "</td>");
                out.println("<td>" + p.getKills() + "-" + p.getDeaths() + "</td>");
                out.println("<td>" + p.getAdr() + "</td>");
                out.println("<td>" + p.getKast() + "%</td>");
                out.println("<td>" + p.getRating() + "</td>");
                out.println("<td><a href=\"/performance/update?playerid=" + p.getPlayerId() + "&teamid=" + p.getTeamId() + "&matchid=" + p.getMatchId() + "\">Edit</a></td>");
                out.println("<td><a href=\"/performance/delete?playerid=" + p.getPlayerId() + "&teamid=" + p.getTeamId() + "&matchid=" + p.getMatchId() + "\">Remove</a></td>");
                out.println("</tr>");
            }

        }
    %>

    </table>
</div>
</body>
</html>
