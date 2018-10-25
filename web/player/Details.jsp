<%@ page import="model.Player" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 10/24/2018
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Player player = (Player) request.getAttribute("player");
    List<Double> ratings = (List<Double>) request.getAttribute("ratings");
%>
<html>
<head>
    <title><%= player.getName()%></title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
</head>
<body>
    <div class="container">
        <h1><%= player.getName() %></h1>

        <h2>Ratings</h2>
        <div id="graph" style="width:100%;"></div>

    </div>

    <script>

        data = [{
            type:'scatter',
            x: [0, <% for (int i = 1; i <= ratings.size(); i++) out.print("," + i);%>],
            y: <%=ratings%>
        }]

        Plotly.plot("graph",data)
    </script>
</body>
</html>
