<%--
  Created by IntelliJ IDEA.
  User: neda
  Date: ۲۴/۰۱/۲۰۲۲
  Time: ۱۰:۰۷ بعدازظهر
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>view Category</title>
</head>
<body>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Salary</th>
        <th>Designation</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="category" items="${list}">
        <tr>
            <td>${category.title}</td>
            <td><a href="admin/editCategory/${category.title}">Edit</a></td>
            <td><a href="admin/deleteCategory/${category.title}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="empform">Add New Employee</a>

</body>
</html>
