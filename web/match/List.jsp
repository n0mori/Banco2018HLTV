<%@ page import="model.Player" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Team" %>
<%@ page import="model.Match" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/26/2018
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches - BD 2018</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Lista de Match</h1>

    <a href="/match/create">Create</a>

    <table class="table">
    <thead class="thead-dark">
    <tr>
        <td scope="col">Id</td>
        <td scope="col">URL</td>
        <td scope="col">Home Team</td>
        <td scope="col">Home Score</td>
        <td scope="col"></td>
        <td scope="col">Away Score</td>
        <td scope="col">Away Team</td>
        <td scope="col">Event URL</td>
        <td scope="col">Date</td>
        <td scope="col">Best of</td>
        <td scope="col"></td>
        <td scope="col"></td>
        <td scope="col"></td>
    </tr>
    </thead>

    <%
        List<Match> list = (List<Match>) request.getAttribute("matchList");


        if (list != null) {
            for (Match t : list) {
                out.println("<tr>");
                out.println("<td><a href=\"/match/details?id=" + t.getId() + "\">" + t.getId() + "</a></td>");
                out.println("<td><a href=\"" + t.getUrl() + "\">" + "Link</a></td>");
                out.println("<td>" + t.getHomeId() + "</td>");
                out.println("<td>" + t.getHomeScore() + "</td>");
                out.println("<td>vs</td>");
                out.println("<td>" + t.getAwayScore() + "</td>");
                out.println("<td>" + t.getAwayId() + "</td>");
                out.println("<td><a href=\"" + t.getEventUrl() + "\">" + "Link</a></td>");
                out.println("<td>" + t.getDate().toString() + "</td>");
                out.println("<td>" + t.getBestOf() + "</td>");
                out.println("<td><a href=\"/match/update?id=" + t.getId() + "\">Edit</a></td>");
                out.println("<td><a href=\"/match/delete?id=" + t.getId() + "\">Remove</a></td>");
                out.println("</tr>");
            }

        }
    %>

    </table>
</div>
</body>
</html>
