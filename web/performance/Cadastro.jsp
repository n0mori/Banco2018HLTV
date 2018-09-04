<%@ page import="model.Player" %>
<%@ page import="model.Team" %>
<%@ page import="model.Match" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/23/2018
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Performance - Cadastro</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%
    List<Player> playerList = (List<Player>) request.getAttribute("playerList");
    List<Team> TeamList = (List<Team>) request.getAttribute("teamList");
    List<Match> matchList = (List<Match>) request.getAttribute("matchList");
%>
<div class="container">
<h1>Performance - Create</h1>
<form action="/team/create" method="post">
    <div class="form-group">
        <label for="playerid">Player ID</label>
        <select id="playerid" name="playerid" class="form-group">
            <%
                for (Player p : playerList) {
                    out.println("<option value=\"" + p.getId() + "\">" + p.getName() + "</option>");
                }
            %>
        </select>
    </div>
    <div class="form-group">
        <label for="teamid">Player ID</label>
        <select id="teamid" name="teamid" class="form-group">
            <%
                for (Team t : teamList) {
                    out.println("<option value=\"" + t.getId() + "\">" + t.getName() + "</option>");
                }
            %>
        </select>
    </div>
    <div class="form-group">
        <label for="matchid">Player ID</label>
        <select id="matchid" name="matchid" class="form-group">
            <%
                for (Match m : matchList) {
                    out.println("<option value=\"" + m.getId() + "\">" m.getHomeId() + " x " m.getAwayId() + "</option>");
                }
            %>
        </select>
    </div>
    <div class="form-group">
        <label for="kills">Nome</label><input type="number" class="form-text" id="kills" name="kills" step="1">
    </div>
    <div class="form-group">
        <label for="deaths">Nome</label><input type="number" class="form-text" id="deaths" name="deaths" step="1">
    </div>
    <div class="form-group">
        <label for="adr">Nome</label><input type="number" class="form-text" id="adr" name="adr">
    </div>
    <div class="form-group">
        <label for="kast">Nome</label><input type="number" class="form-text" id="kast" name="kast">
    </div>
    <div class="form-group">
        <label for="rating">Nome</label><input type="number" class="form-text" id="rating" name="rating">
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-primary">
    </div>
</form>
</div>
</body>
</html>
