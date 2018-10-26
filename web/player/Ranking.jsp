<%@ page import="model.Performance" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Player" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 10/26/2018
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rankings</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>

<%
    List<Performance> ranking = (List<Performance>) request.getAttribute("ranking");
%>


<div class="container">
    <h1>Ranking - Players</h1>

    <table class="table">
        <thead class="thead-dark">
            <tr>
                <td></td>
                <td>Player</td>
                <td>Rating</td>
                <td>Nacionalidade</td>
            </tr>
        </thead>

        <%
            int pos = 1;
            for (Performance performance : ranking) {
                Player p = performance.getPlayer();
        %>
            <tr>
                <td><%=pos%></td>
                <td><a href="/player/details?id=<%=p.getId()%>"><%=p.getName()%></a></td>
                <td><%=performance.getRating()%></td>
                <td><%=p.getNationality()%></td>
            </tr>

        <%
                pos++;
            }
        %>

    </table>

</div>

</body>
</html>
