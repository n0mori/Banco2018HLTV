<%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 8/23/2018
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team - Cadastro</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
<h1>Team - Create</h1>
<form action="/team/create" method="post">
    <div class="form-group">
        <label for="id">Id HLTV</label>
        <input type="number" class="form-control" id="id" name="id" step="1">
    </div>
    <div class="form-group">
        <label for="name">Nome</label><input type="text" class="form-control" id="name" name="name">
    </div>
    <div class="form-group">
        <label for="url">URL HLTV</label><input type="text" class="form-control" id="url" name="url">
    </div>
    <div class="form-group">
        <label for="nationality">Nacionalidade</label><input type="text" class="form-control" id="nationality" name="nationality">
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-primary">
    </div>
</form>
</div>
</body>
</html>
