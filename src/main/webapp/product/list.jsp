<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 11/11/2021
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View</title>
    <link rel="stylesheet" href="../list.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../IMAGINE">
</head>
<body>
<div class="container-sm">
    <h2 class="tit">Hoang Duy Mobile</h2>
    <a href="/products" style="text-decoration: none; color: PURPLE">HOME PAGE</a><BR>
    <a class="tit" href="/products?action=create" style="border: none">Create new Products</a><br>
    <a href="/products?action=searchP" style="text-decoration: none; color: orange; border: none">Search</a><BR>
    <a href="/products?action=sortByName" style="text-decoration: none; color: orange; border: none">Sort</a><br><br>

    <form>
        <table>
            <caption class="cap">Product list</caption>
            <tr>
                <th style="width: 5%">ID</th>
                <th style="width: 30%">NAME</th>
                <th>DESCRIPTION</th>
                <th>PRODUCER</th>
                <th>PRICE</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
            <c:forEach var="product" items="${list}">
            <tr>
                <td><c:out value="${product.id}"/></td>
                <td><c:out value="${product.name}"/></td>
                <td>
                    <a href="/products?action=searchD&searchD=${product.getDescription()}"
                       style="color: black; border: none; text-decoration: none"><c:out
                            value="${product.description}"/></a>
                </td>
                <td>
                    <a href="/products?action=search&searchP=${product.getProducer()}"
                       style="color: black; border: none; text-decoration: none"><c:out
                            value="${product.producer}"/></a>

                </td>
                <td><c:out value="${product.price}"/></td>
                <td>
                    <a style="color: blue;border: none; text-decoration: none"
                       href="/products?action=edit&id=${product.id}">Edit</a>
                </td>
                <td>
                    <a style="color: blue;border: none; text-decoration: none"
                       href="/products?action=delete&id=${product.id}">Delete</a>
                </td>
            </tr>
            </c:forEach>
    </form>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
