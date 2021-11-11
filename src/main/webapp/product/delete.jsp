<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 11/11/2021
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
<fieldset style="width: 25%;">
    <lengend>PRODUCT DELETE</lengend>
    <p>DO YOU WANT TO DELETE THIS PRODUCT</p>
    <form method="post">
        <p>ID: ${productDelete.getId()}</p>
        <p>Name: ${productDelete.getName()}</p>
        <p>Description: ${productDelete.getDescription()}</p>
        <p>Producer: ${productDelete.getProducer()}</p>
        <p>Price: ${productDelete.getPrice()}</p>
        <button type="submit">Submit</button>
        <button type="button">
            <a href="/products">Back</a>
            </button>

    </form>
</fieldset>

</body>
</html>
