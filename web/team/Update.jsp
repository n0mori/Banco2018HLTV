<%@ page import="model.Team" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/27/2018
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team - Update</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Team - Update</h1>
    <%
        Team team = (Team) request.getAttribute("team");

    %>
    <form action="/team/update" method="post">
        <div class="form-group">
            <fieldset class="disabled">
                <label for="id">Id Team HLTV</label>
                <input type="number" class="form-text" id="id" name="id" step="1" value="<%= team.getId() %>" required readonly>
            </fieldset>
        </div>
        <div class="form-group">
            <label for="name">Nome</label><input type="text" class="form-text" id="name" name="name" value="<%= team.getName()%>" required>
        </div>
        <div class="form-group">
            <label for="url">URL HLTV</label><input type="text" class="form-text" id="url" name="url" value="<%= team.getUrl()%>" required>
        </div>
        <div class="form-group">
            <label for="nationality">Nacionalidade</label><input type="text" class="form-text" id="nationality" name="nationality" value="<%= team.getNationality()%>" required>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary">
        </div>
    </form>
</div>

</body>
</html>
