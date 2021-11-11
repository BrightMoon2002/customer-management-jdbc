<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 11/11/2021
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>View</title>
    <link rel="stylesheet" href="../list.css">
</head>
<body>
<h2 class="tit">Hoang Duy mobile</h2>
<fieldset style="width: 25%">
    <length>Seacrch Producer</length>
    <form method="post">
        <input type="text" name="searchP" placeholder="Producer"><br>
        <button type="submit">Search</button>
        <button type="reset">Clear</button>
        <a href="/products" style="border-radius: 5px; color: black; border-color: grey; text-decoration: none; font-size: small">Back</a>
    </form>
</fieldset>

</body>
</html>
