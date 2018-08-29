<%@ page import="model.Player" %><%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/27/2018
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Player - Update</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Player - Update</h1>
    <%
        Player player = (Player) request.getAttribute("player");

        if (player == null) {

        }
    %>
    <form action="/player/update" method="post">
        <div class="form-group">
            <fieldset class="disabled">
                <label for="id">Id HLTV</label>
                <input type="number" class="form-text" id="id" name="id" step="1" contenteditable="false" value="<%= player.getId() %>">
            </fieldset>
        </div>
        <div class="form-group">
            <label for="name">Nome</label><input type="text" class="form-text" id="name" name="name" value="<%= player.getName()%>">
        </div>
        <div class="form-group">
            <label for="url">URL HLTV</label><input type="text" class="form-text" id="url" name="url" value="<%= player.getUrl()%>">
        </div>
        <div class="form-group">
            <label for="nationality">Nacionalidade</label><input type="text" class="form-text" id="nationality" name="nationality" value="<%= player.getNationality()%>">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary">
        </div>
    </form>
</div>

</body>
</html>
