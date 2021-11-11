<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 11/11/2021
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new product</title>
</head>
<body>
<h2>Create new product</h2>
<a href="/products">Home page</a>
<form method="post">
    <fieldset style="width: 25%">
        <legend>Create new product</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td>Description: </td>
                <td>
                    <input type="text" name="description">
                </td>
            </tr>
            <tr>
                <td>Producer: </td>
                <td>
                    <input type="text" name="producer">
                </td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>
                    <input type="text" name="price">
                </td>
            </tr>
        </table>
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </fieldset>

</form>


</body>
</html>
