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
    <title>Produto</title>

</head>
<body>
<form action="/player/create" method="post">
    <p>Id HLTV</p><input type="number" name="id" step="1">
    <p>Nome</p><input type="text" name="nome">
    <p>URL HLTV</p><input type="text" name="url">
    <p>Nacionalidade</p><input type="text" name="nationality">
    <input type="submit">
</form>
</body>
</html>
