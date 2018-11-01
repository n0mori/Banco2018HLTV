<%@ page import="model.Performance" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Match" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 10/23/2018
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Performance> performances = (List<Performance>) request.getAttribute("performanceList");
    Match match = (Match) request.getAttribute("match");
    List<Match> matches = (List<Match>) request.getAttribute("matches");
%>
<html>
<head>
    <title><%=match.getHomeTeam().getName()%> vs <%=match.getAwayTeam().getName()%></title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1><%=match.getHomeTeam().getName()%> <%=match.getHomeScore()%> x <%=match.getAwayScore()%> <%=match.getAwayTeam().getName()%> </h1>

    <table class="table table-striped">
        <thead class="thead-dark">
        <td scope="col">Team</td>
        <td scope="col">Player</td>
        <td scope="col">K-D</td>
        <td scope="col">ADR</td>
        <td scope="col">KAST</td>
        <td scope="col">Rating</td>
        <td scope="col"></td>
        <td scope="col"></td>
        </thead>

        <%
            int i = 0;
            for (Performance p : performances) {
                //if (i == 5) out.println("<tr><br></tr>");
                //i++;
        %>

        <tr>
            <td><%=p.getTeam().getName()%></td>
            <td><a href="/player/details?id=<%=p.getPlayerId()%>"><%=p.getPlayer().getName()%></a></td>
            <td><%=p.getKills()%>-<%=p.getDeaths()%></td>
            <td><%=p.getAdr()%></td>
            <td><%=p.getKast()%></td>
            <td><%=p.getRating()%></td>
        </tr>

        <%
            };
        %>
    </table>

    <h2>Confrontos</h2>

    <table class="table table-striped">
        <%
            for (Match m : matches) {
        %>

        <tr>
            <td><a href="/match/details?id=<%=m.getId()%>">Detalhes</a></td>
            <td><%=m.getHomeTeam().getName()%></td>
            <td><%=m.getHomeScore()%></td>
            <td>vs</td>
            <td><%=m.getAwayScore()%></td>
            <td><%=m.getAwayTeam().getName()%></td>
            <td><%if (m.getId() == match.getId()) out.println("*");%></td>
        </tr>

        <%
            }
        %>
    </table>

</div>
</body>
</html>
