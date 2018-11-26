<%@ page import="model.Team" %>
<%@ page import="model.Match" %>
<%@ page import="model.Player" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 11/25/2018
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Team team = (Team) request.getAttribute("team");
    Match m = (Match) request.getAttribute("match");
    double winRate = (double) request.getAttribute("winRate");
    List<Player> players = (List<Player>) request.getAttribute("players");
%>
<html>
<head>
    <title><%=team.getName()%></title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1><%=team.getName()%> - Details</h1>
    <a href="<%=team.getUrl()%>">HLTV URL</a>
    <p>Nationality: <%=team.getNationality()%></p>

    <h2>Win Rate:</h2>
    <p><%=Math.floor(winRate)%>%</p>


    <h2>Partida mais recente</h2>
    <table class="table table-striped">

        <tr>
            <td><a href="/match/details?id=<%=m.getId()%>">Detalhes</a></td>
            <td><%=m.getHomeTeam().getName()%></td>
            <td><%=m.getHomeScore()%></td>
            <td>vs</td>
            <td><%=m.getAwayScore()%></td>
            <td><%=m.getAwayTeam().getName()%></td>
        </tr>
    </table>

    <h2>Lineup mais recente</h2>
    <table class="table table-striped">
        <%
            for (Player p : players) {
        %>

        <tr>
            <td><a href="/player/details?id=<%=p.getId()%>">Detalhes</a></td>
            <td><%=p.getName()%></td>
            <td><%=p.getNationality()%></td>
        </tr>

        <%
            }
        %>
    </table>

</div>
</body>
</html>
