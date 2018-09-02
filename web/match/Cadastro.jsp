<%@ page import="model.Team" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/23/2018
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match - Cadastro</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
<h1>Match - Create</h1>
<form action="/match/create" method="post">
    <%
        List<Team> teamList = (List<Team>) request.getAttribute("teamList");
    %>
    <div class="form-group">
        <label for="id">Id HLTV</label>
        <input type="number" class="form-text" id="id" name="id" step="1">
    </div>
    <div class="form-group">
        <label for="url">URL HLTV</label><input type="text" class="form-text" id="url" name="url">
    </div>
    <div class="form-group">
        <label for="homeId">Home Team</label>
        <select class="form-control" name="homeId" id="homeId">
            <%
                for (Team t : teamList) {
                    out.println("<option value=\"" + t.getId() + "\">" + t.getName() + "</option>");
                }
            %>
        </select>
    </div>
    <div class="form-group">
        <label for="homeScore">Home Score</label>
        <input type="number" class="form-text" step="1" name="homeScore" id="homeScore">
    </div>
    <div class="form-group">
        <label for="awayId">Away Team</label>
        <select class="form-control" name="awayId" id="awayId">
            <%
                for (Team t : teamList) {
                    out.println("<option value=\"" + t.getId() + "\">" + t.getName() + "</option>");
                }
            %>
        </select>
    </div>
    <div class="form-group">
        <label for="awayScore">Away Score</label>
        <input type="number" class="form-text" step="1" name="awayScore" id="awayScore">
    </div>
    <div class="form-group">
        <label for="eventUrl">Event URL HLTV</label><input type="text" class="form-text" id="eventUrl" name="eventUrl">
    </div>
    <div class="form-group">
        <label for="bestOf">Best Of</label>
        <input type="number" class="form-text" step="1" name="bestOf" id="bestOf">
    </div>
    <div class="form-group">
        <label for="date">Date</label>
        <input type="date" class="form-text" name="date" id="date">
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-primary">
    </div>

</form>
</div>
</body>
</html>
