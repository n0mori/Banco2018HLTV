<%@ page import="model.Stats" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 10/29/2018
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Stats> nations = (List<Stats>) request.getAttribute("nations");%>
<html>
<head>
    <title>Stats</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Stats</h1>
    <div class="container">
        <h2>Nationality</h2>

        <div id="nations"></div>

        <script>
            data = [{
                type:'pie',
                values: [<%
                    for (int i = 0; i < nations.size(); i++) {
                        if (i == 0) {
                            out.print(nations.get(i).getCount());
                        } else {
                            out.print("," + nations.get(i).getCount());
                        }
                    }
                %>],
                labels: [<%
                    for (int i = 0; i < nations.size(); i++) {
                        if (i == 0) {
                            out.print("\"" + nations.get(i).getNationality() + "\"");
                        } else {
                            out.print(",\"" + nations.get(i).getNationality() + "\"");
                        }
                    }
                %>]
            }]

            Plotly.plot("nations",data)


        </script>

    </div>
</div>
</body>
</html>
