<%@ page import="model.Team" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Match" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/27/2018
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match - Update</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Match - Update</h1>
    <%
        Match match = (Match) request.getAttribute("match");
        List<Team> teamList = (List<Team>) request.getAttribute("teamList");
    %>
    <form action="/match/update" method="post">
        <div class="form-group">
            <label for="id">Id HLTV</label>
            <input type="number" class="form-text" id="id" name="id" step="1" value="<%= match.getId()%>" readonly>
        </div>
        <div class="form-group">
            <label for="url">URL HLTV</label><input type="text" class="form-control" id="url" name="url" value="<%= match.getUrl()%>">
        </div>
        <div class="form-group">
            <label for="homeId">Home Team</label>
            <select class="form-control" name="homeId" id="homeId">
                <%
                    for (Team t : teamList) {
                        if (match.getHomeId() == t.getId()) {
                            out.println("<option value=\"" + t.getId() + "\" selected>" + t.getName() + "</option>");
                        } else {
                            out.println("<option value=\"" + t.getId() + "\">" + t.getName() + "</option>");
                        }
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="homeScore">Home Score</label>
            <input type="number" class="form-control" step="1" name="homeScore" id="homeScore" value="<%=match.getHomeScore()%>">
        </div>
        <div class="form-group">
            <label for="awayId">Away Team</label>
            <select class="form-control" name="awayId" id="awayId">
                <%
                    for (Team t : teamList) {
                        if (match.getAwayId() == t.getId()) {
                            out.println("<option value=\"" + t.getId() + "\" selected>" + t.getName() + "</option>");
                        } else {
                            out.println("<option value=\"" + t.getId() + "\">" + t.getName() + "</option>");
                        }
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="awayScore">Away Score</label>
            <input type="number" class="form-control" step="1" name="awayScore" id="awayScore" value="<%=match.getAwayScore()%>">
        </div>
        <div class="form-group">
            <label for="eventUrl">Event URL HLTV</label><input type="text" class="form-control" id="eventUrl" name="eventUrl" value="<%=match.getEventUrl()%>">
        </div>
        <div class="form-group">
            <label for="bestOf">Best Of</label>
            <input type="number" class="form-control" step="1" name="bestOf" id="bestOf" value="<%=match.getBestOf()%>">
        </div>
        <div class="form-group">
            <label for="date">Date</label>
            <input type="date" class="form-control" name="date" id="date" value="<%=match.getDate().toString()%>">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary">
        </div>

    </form>
    </form>
</div>

</body>
</html>
