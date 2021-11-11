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
    <title>Edit Product</title>
</head>
<body>
<fieldset style="width: 25%">
    <legend>Edit Product</legend>
    <form method="post">
        <table>
            <tr><td> Name: </td>
            <td>
                <input type="text" name="name" value="${productEdit.getName()}">
            </td>
            </tr>
            <tr><td>Description:  </td>
            <td>
                <input type="text" name="description" value="${productEdit.getDescription()}">
            </td>
            </tr>
            <tr> <td>Producer: </td>
            <td>
                <input type="text" name="producer" value="${productEdit.getProducer()}">
            </td>
            </tr>
            <tr> <td>Price:  </td>
            <td>
                <input type="number" name="price" value="${productEdit.getPrice()}">
            </td>
            </tr>
        </table>
        <button type="submit">Submit</button>
        <button type="reset">Clear</button>
    </form>
    <a href="/products">Home page</a>
</fieldset>

</body>
</html>
