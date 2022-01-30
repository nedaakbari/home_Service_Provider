<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminLogin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<form:form cssClass="p-3 m-3" cssStyle="" modelAttribute="admin" action="admin/adminLogin" method="post">
    <p class="text-danger">${error}</p>
    <table class="table table-bordered table-striped text-info">
        <tr>
            <td><form:label path="userName">UserName :</form:label></td>
            <td><form:input path="userName" name="userName"/></td>
        </tr>

        <tr>
            <td></td>
            <td><form:errors path="userName" cssClass="text-danger"/></td>
        </tr>

        <tr>
            <td><form:label path="passWord">passWord(*) :</form:label></td>
            <td><form:input path="passWord" name="passWord"/></td>
        </tr>
        <tr>
            <td></td>
            <td><form:errors path="passWord" cssClass="text-danger"/></td>
        </tr>

        <tr>
            <td>
            </td>
            <td>
                <form:button name="admin/adminLogin">Login</form:button>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
