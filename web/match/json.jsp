<%--
  Created by IntelliJ IDEA.
  User: n0mori
  Date: 9/3/2018
  Time: 11:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches - JSON</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form action="/match/json" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="json">JSON</label>
            <input type="file" id="json" name="json" class="form-control-file" accept="application/json">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary">
        </div>
    </form>
</div>
</body>
</html>
