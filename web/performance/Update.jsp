<%@ page import="model.Team" %>
<%@ page import="model.Player" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Match" %>
<%@ page import="model.Performance" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/27/2018
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Performance - Update</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%
    Performance performance = (Performance) request.getAttribute("performance");
%>
<div class="container">
    <h1>Performance - Update</h1>
    <form action="/team/create" method="post">
        <div class="form-group">
            <label for="playerid">Player</label>
            <input id="playerid" name="playerid" class="form-control" value="<%=performance.getPlayerId()%>" readonly>
        </div>
        <div class="form-group">
            <label for="teamid">Team</label>
            <input id="teamid" name="teamid" class="form-control" value="<%=performance.getTeamId()%>" readonly>
        </div>
        <div class="form-group">
            <label for="matchid">Match</label>
            <input id="matchid" name="matchid" class="form-control" value="<%=performance.getMatchId()%>" readonly>
        </div>
        <div class="form-group">
            <label for="kills">Kills</label><input type="number" class="form-control" id="kills" name="kills" step="1" value="<%=performance.getKills()%>">
            </div>
        <div class="form-group">
            <label for="deaths">Deaths</label><input type="number" class="form-control" id="deaths" name="deaths" step="1" value="<%=performance.getDeaths()%>">
        </div>
        <div class="form-group">
            <label for="adr">ADR</label><input type="number" class="form-control" id="adr" name="adr" value="<%=performance.getAdr()%>">
        </div>
        <div class="form-group">
            <label for="kast">KAST</label><input type="number" class="form-control" id="kast" name="kast" value="<%=performance.getKast()%>">
        </div>
        <div class="form-group">
            <label for="rating">Rating</label><input type="number" class="form-control" id="rating" name="rating" value="<%=performance.getRating()%>">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary">
        </div>
    </form>
</div>
</body>
</html>
